package com.app.bookJeog.controller;

import com.app.bookJeog.controller.exception.ResourceNotFoundException;
import com.app.bookJeog.domain.dto.*;
import com.app.bookJeog.domain.enumeration.MemberType;
import com.app.bookJeog.domain.enumeration.PostType;
import com.app.bookJeog.domain.vo.CommentVO;
import com.app.bookJeog.domain.vo.PostVO;
import com.app.bookJeog.domain.vo.SponsorMemberVO;
import com.app.bookJeog.service.*;
import com.app.bookJeog.controller.exception.UnauthenticatedException;
import com.app.bookJeog.domain.dto.BookPostMemberDTO;
import com.app.bookJeog.domain.dto.FileBookPostDTO;
import com.app.bookJeog.domain.dto.PersonalMemberDTO;
import com.app.bookJeog.service.PostService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/post/*")
public class PostController {
    private final PostService postService;
    private final AladinService aladinService;
    private final BookService bookService;
    private final CommentService commentService;
    private final MemberService memberService;
    private final FIleService fileService;

    // 토론게시판 이동
    @GetMapping("discussion")
    public String goToDiscussion(Model model) {
        List<DiscussionPostDTO> postList = postService.getAllDiscussions();
        for (DiscussionPostDTO post : postList) {
            post.setImageUrl(aladinService.getBookCover(post.getBookIsbn()));
        }
        model.addAttribute("discussions", postList);
        return "discussion/main";
    }


    // 토론 게시글
    @GetMapping("discussion/post/{id}")
    public String goToDiscussionPost(@PathVariable Long id, Model model, HttpSession session) {

        DiscussionPostDTO post = postService.getDiscussionById(id);
        post.setImageUrl(aladinService.getBookCover(post.getBookIsbn()));
        model.addAttribute("member", session.getAttribute("member"));
        model.addAttribute("discussion", post);
        List<CommentInfoDTO> commentList = new ArrayList<>();
        List<CommentVO> tempList = commentService.getAllCommentByPostId(id);
        for(CommentVO commentVO : tempList) {
            CommentInfoDTO commentInfoDTO = new CommentInfoDTO();
            CommentDTO commentDTO = commentService.toCommentDTO(commentVO);
            if(commentService.getMentionedId(commentDTO.getId()) != null){
                Long mentionedId = commentService.getMentionedId(commentDTO.getId());
                commentInfoDTO.setMentionedName(memberService.getMemberName(mentionedId));
            }

            commentInfoDTO.setCommentDTO(commentDTO);
//
            String memberName = "";
            MemberType memberType = memberService.getMemberType(commentDTO.getMemberId());

            switch (memberType) {
                case PERSONAL -> memberName = memberService.getPersonalMember(commentDTO.getMemberId()).getMemberName();

                case SPONSOR -> memberName = memberService.getSponsorMemberById(commentDTO.getMemberId()).getSponsorName();
                }
                commentInfoDTO.setMemberName(memberName);
                commentList.add(commentInfoDTO);
            }
        log.info(commentList.toString());
        model.addAttribute("comments", commentList);

        List<CommentVO> comments = commentService.getAllMembersByPostId(id);
        List<CommentInfoDTO> mentionList = new ArrayList<>();
        for(CommentVO comment : comments) {
            CommentInfoDTO commentInfoDTO = new CommentInfoDTO();
            CommentDTO commentDTO = commentService.toCommentDTO(comment);
            commentInfoDTO.setCommentDTO(commentDTO);
            String memberName = "";
            MemberType memberType = memberService.getById(commentDTO.getMemberId()).getMemberType();

            switch (memberType) {
                case PERSONAL -> memberName = memberService.getPersonalMember(commentDTO.getMemberId()).getMemberName();

                case SPONSOR -> memberName = memberService.getSponsorMemberById(commentDTO.getMemberId()).getSponsorName();
            }
            commentInfoDTO.setMemberName(memberName);
            mentionList.add(commentInfoDTO);
        }

        model.addAttribute("mentions", mentionList);
        return "discussion/post";
    }


    // 독후감 게시판
    @GetMapping("bookpost")
    public String goToBookPost() {
        return "post/post-list";
    }

    // 전체 피드 조회
    @GetMapping("/all-book-post")
    @ResponseBody
    public List<FileBookPostDTO> goToAllBookPost(@RequestParam(value = "offset", defaultValue = "0") int offset) {
        return postService.findAllBookPostFeed(offset);
    }

    // 팔로잉 피드 조회
    @GetMapping("/following-book-post")
    @ResponseBody
    public List<FileBookPostDTO> goToFollowingBookPost(@RequestParam(value = "offset", defaultValue = "0") int offset, HttpSession session) {
        PersonalMemberDTO member = (PersonalMemberDTO) session.getAttribute("member");
        if(member != null) {
            Long loginMemberId = member.getId();
            return postService.findFollowBookPostFeed(loginMemberId, offset);
        }
        return List.of();
    }

    // 독후감 게시글
    @GetMapping("bookpost/{id}")
    public String goToBookPostPost(@PathVariable Long id, Model model, HttpSession session) {
        FileBookPostDTO post = postService.getPostWithFiles(id);
        if (post == null) {
            throw new ResourceNotFoundException("게시글이 존재하지 않습니다.");
        }
        model.addAttribute("post", post);

        // 세션의 회원 id도 같이 저장
        PersonalMemberDTO member = (PersonalMemberDTO)session.getAttribute("member");
        if (member != null) {
            Long loginId = member.getId();
            model.addAttribute("loginId", loginId);
        }

        return "post/post-detail";
    }


    // 독후감 작성
    @GetMapping("bookpost/write")
    public String goToBookPostWrite(HttpSession session, Model model, RedirectAttributes redirectAttributes) {
        PersonalMemberDTO member = (PersonalMemberDTO)session.getAttribute("member");
        if(member == null) {
            throw new UnauthenticatedException("로그인이 필요한 서비스입니다.");
        } else{
            model.addAttribute("memberName", member.getMemberName());
            return "post/post-write";
        }
    }

    @PostMapping("bookpost/write")
    public String writeBookPost(@ModelAttribute("post") FileBookPostDTO fileBookPostDTO,
                                @RequestParam("file") List<MultipartFile> files, RedirectAttributes redirectAttributes,
                                HttpSession session) {
        fileBookPostDTO.setMemberId(((PersonalMemberDTO)session.getAttribute("member")).getId());
        Long newBookPostId = postService.write(fileBookPostDTO, files);
        redirectAttributes.addFlashAttribute("message", "독후감 작성 완료!");
        return "redirect:/post/bookpost/" + newBookPostId;
    }

    // 독후감 수정
    @GetMapping("bookpost/edit/{bookPostId}")
    public String goToBookPostEdit(@PathVariable Long bookPostId, Model model, HttpSession session) {
        PersonalMemberDTO member = (PersonalMemberDTO)session.getAttribute("member");
        if(member == null) {
            return "redirect:/personal/login";
        }
        // 독후감 정보
        FileBookPostDTO fileBookPostDTO = postService.findWrittenBookPost(bookPostId);
        log.info("fileBookPostDTO = {}", fileBookPostDTO);

        // 첨부파일 목록
        List<BookPostFileDTO> fileList = postService.findWrittenBookPostFiles(bookPostId);
        fileBookPostDTO.setFileList(fileList);

        model.addAttribute("fileBookPostDTO", fileBookPostDTO);
        return "post/post-update";
    }

    @PostMapping("bookpost/edit")
    public String editBookPost(@ModelAttribute FileBookPostDTO fileBookPostDTO,
                               @RequestParam(value = "deleteFileIds", required = false)List<Long> deletedFileIds) {
        log.info("📥 DTO 값 확인: " + fileBookPostDTO);
        postService.setBookPost(fileBookPostDTO, deletedFileIds);
        return "redirect:/post/bookpost/" + fileBookPostDTO.getBookPostId(); // 수정 후 상세페이지로
    }

    // 후원 인증 게시판
    @GetMapping("donate")
    public String goToDonateCert(Model model){
        List<DonateCertPostDTO> postList = postService.getAllDonateCerts();
        for (DonateCertPostDTO post : postList) {
            post.setSponsorName(memberService.getSponsorMemberById(post.getMemberId()).getSponsorName());
            post.setCommentCount(commentService.getAllCommentByPostId(post.getId()).size());
            if(fileService.getDonateCertFileByPostId(post.getId()) != null){
                post.setDonateCertFileName(fileService.getDonateCertFileByPostId(post.getId()).getFileName());
                post.setDonateCertFilePath(fileService.getDonateCertFileByPostId(post.getId()).getFilePath());
            }

//          // 이미지 추가하면 좀 더 추가
        }
//        log.info(postList.toString());
        model.addAttribute("DonateCerts",postList);
//        log.info(model.getAttribute("DonateCerts").toString());
        return "donation/donate_cert_main";
    }


    // 후원 인증 게시글    
    @GetMapping("donate/post/{postId}")
    public String goTODonateCertPost(@PathVariable Long postId, Model model, HttpSession session){
        model.addAttribute("DonateCert",postService.getDonateCertById(postId));
        model.addAttribute("member", session.getAttribute("member"));
        List<CommentInfoDTO> commentList = new ArrayList<>();
        List<CommentVO> tempList = commentService.getAllCommentByPostId(postId);
        for(CommentVO commentVO : tempList) {
            CommentInfoDTO commentInfoDTO = new CommentInfoDTO();
            CommentDTO commentDTO = commentService.toCommentDTO(commentVO);
            commentInfoDTO.setCommentDTO(commentDTO);
            log.info(commentDTO.toString());
            String memberName = "";
            MemberType memberType = memberService.getById(commentDTO.getMemberId()).getMemberType();

            switch (memberType) {
                case PERSONAL -> memberName = memberService.getPersonalMember(commentDTO.getMemberId()).getMemberName();

                case SPONSOR -> memberName = memberService.getSponsorMemberById(commentDTO.getMemberId()).getSponsorName();
            }
            commentInfoDTO.setMemberName(memberName);
            commentList.add(commentInfoDTO);
        }
        model.addAttribute("comments", commentList);

        List<CommentVO> comments = commentService.getAllMembersByPostId(postId);
        List<CommentInfoDTO> mentionList = new ArrayList<>();
        for(CommentVO comment : comments) {
            CommentInfoDTO commentInfoDTO = new CommentInfoDTO();
            CommentDTO commentDTO = commentService.toCommentDTO(comment);
            commentInfoDTO.setCommentDTO(commentDTO);
            String memberName = "";
            MemberType memberType = memberService.getById(commentDTO.getMemberId()).getMemberType();

            switch (memberType) {
                case PERSONAL -> memberName = memberService.getPersonalMember(commentDTO.getMemberId()).getMemberName();

                case SPONSOR -> memberName = memberService.getSponsorMemberById(commentDTO.getMemberId()).getSponsorName();
            }
            commentInfoDTO.setMemberName(memberName);
            mentionList.add(commentInfoDTO);
        }

        model.addAttribute("mentions", mentionList);

        return "donation/donate_cert_post";
    }


    // 후원 인증 게시글 작성
    @GetMapping("donate/write")
    public String goToDonateCertWrite(){

        return "donation/donate_cert_write";
    }

    @PostMapping("donate/write")
    public RedirectView write(@RequestParam String title, @RequestParam String content, @RequestParam(required = false) List<MultipartFile> files, HttpSession session) {
        log.info(title);
        log.info(content);
        log.info(files.toString());
        DonateCertDTO donateCertDTO = new DonateCertDTO();
        SponsorMemberVO foundMember = (SponsorMemberVO) session.getAttribute("sponsorMember");
        PostDTO postDTO = new PostDTO();

        postDTO.setPostType(PostType.DONATE_CERT);
        postDTO.setMemberId(foundMember.getId());
        PostVO postVO = postDTO.toVO();
        postService.insertPost(postVO);
        Long postId = postVO.getId();
        donateCertDTO.setId(postId);
        donateCertDTO.setDonateCertTitle(title);
        donateCertDTO.setDonateCertText(content);
        log.info(postVO.toString());
        log.info(donateCertDTO.toString());
        postService.setDonateCertPost(donateCertDTO.toVO());
        fileService.uploadDonateCertFiles(postId, files);

        return new RedirectView("/post/donate");
    }

    // 후원 대상 게시판
    @GetMapping("receiver")
    public String goToReceiver(Model model){
        // 기부 도서 조회
        List<BookDonateInfoDTO> donateList = new ArrayList<>();
        List<BookDonateDTO> tempList = postService.getDonateBooks();
        log.info(tempList.toString());
        for(BookDonateDTO bookDonateDTO : tempList){
            BookDonateInfoDTO donateInfoDTO = new BookDonateInfoDTO();
            donateInfoDTO.setBookDonateDTO(bookDonateDTO);
            donateInfoDTO.setImageUrl(aladinService.getBookCover(bookDonateDTO.getBookIsbn()));
            donateInfoDTO.setAuthor(bookService.getBookByIsbn(bookDonateDTO.getBookIsbn()).get(0).getAuthor());
            donateList.add(donateInfoDTO);
        }
        log.info(donateList.toString());
        model.addAttribute("donateList", donateList);
        //  게시글 조회
        List<ReceiverPostDTO> receiverPostDTOList = postService.getReceiverPosts();

        model.addAttribute("Posts", receiverPostDTOList);
        log.info(receiverPostDTOList.toString());
        return "donation/receiver_main";
    }


    // 후원 대상 게시글
    @GetMapping("receiver/post/{postId}")
    public String goToReceiverPost(Model model, @PathVariable Long postId, HttpSession session){

        model.addAttribute("post", postService.getReceiverPostById(postId));
        model.addAttribute("member", session.getAttribute("member"));
        List<CommentInfoDTO> commentList = new ArrayList<>();
        List<CommentVO> tempList = commentService.getAllCommentByPostId(postId);
        for(CommentVO commentVO : tempList) {
            CommentInfoDTO commentInfoDTO = new CommentInfoDTO();
            CommentDTO commentDTO = commentService.toCommentDTO(commentVO);
            commentInfoDTO.setCommentDTO(commentDTO);
            log.info(commentDTO.toString());
            String memberName = "";
            MemberType memberType = memberService.getById(commentDTO.getMemberId()).getMemberType();

            switch (memberType) {
                case PERSONAL -> memberName = memberService.getPersonalMember(commentDTO.getMemberId()).getMemberName();

                case SPONSOR -> memberName = memberService.getSponsorMemberById(commentDTO.getMemberId()).getSponsorName();
            }
            commentInfoDTO.setMemberName(memberName);
            commentList.add(commentInfoDTO);
        }
        model.addAttribute("comments", commentList);
        List<CommentVO> comments = commentService.getAllMembersByPostId(postId);
        List<CommentInfoDTO> mentionList = new ArrayList<>();
        for(CommentVO comment : comments) {
            CommentInfoDTO commentInfoDTO = new CommentInfoDTO();
            CommentDTO commentDTO = commentService.toCommentDTO(comment);
            commentInfoDTO.setCommentDTO(commentDTO);
            String memberName = "";
            MemberType memberType = memberService.getById(commentDTO.getMemberId()).getMemberType();

            switch (memberType) {
                case PERSONAL -> memberName = memberService.getPersonalMember(commentDTO.getMemberId()).getMemberName();

                case SPONSOR -> memberName = memberService.getSponsorMemberById(commentDTO.getMemberId()).getSponsorName();
            }
            commentInfoDTO.setMemberName(memberName);
            mentionList.add(commentInfoDTO);
        }

        model.addAttribute("mentions", mentionList);
        return "donation/receiver_post";
    }


    // 후원 대상 게시글 작성
    @GetMapping("receiver/write")
    public String goToReceiverWrite(){
        return "donation/receiver_write";
    }


    // 이 주의 기부도서
    @GetMapping("weekly")
    public String goToWeekly(Model model){
        List<BookDonateInfoDTO> donateList = new ArrayList<>();
        List<BookDonateDTO> tempList = postService.getDonateBooks();
        log.info(tempList.toString());
        for(BookDonateDTO bookDonateDTO : tempList){
            BookDonateInfoDTO donateInfoDTO = new BookDonateInfoDTO();
            donateInfoDTO.setBookDonateDTO(bookDonateDTO);
            donateInfoDTO.setImageUrl(aladinService.getBookCover(bookDonateDTO.getBookIsbn()));
            donateInfoDTO.setAuthor(bookService.getBookByIsbn(bookDonateDTO.getBookIsbn()).get(0).getAuthor());
            donateList.add(donateInfoDTO);
        }

        model.addAttribute("donateList", donateList);

        return "donation/weekly_book";
    }

    // 이 책의 일부 독후감들
    @GetMapping("book/book-posts")
    @ResponseBody
    public ArrayList<BookPostMemberDTO> selectThisBookPosts(@RequestParam Long isbn){
        return postService.selectThisBookPosts(isbn);
    }

    // 이 책의 전체 독후감들
    @GetMapping("book/post-list")
    @ResponseBody
    public ArrayList<BookPostMemberDTO> selectThisBookAllPosts(@RequestParam Long isbn, @RequestParam int offset){
        return postService.selectThisBookAllPosts(isbn, offset);
    }

    // 이 책의 전체 독후감들 개수 출력
    @GetMapping("book/post-count")
    @ResponseBody
    public int getBookPostCount(@RequestParam Long isbn) {
        return postService.selectBookAllPostsCount(isbn);
    }

    // 기부글 이미지 출력
    @GetMapping("thumbnail")
    @ResponseBody
    public ResponseEntity<byte[]> getProfileImage(@RequestParam("path") String path,
                                                  @RequestParam("name") String name) throws IOException {
        // 이미지 파일 경로 설정
        File imageFile = new File("C:/upload/" + path.replace("/", File.separator) + "/" + name);

        // 파일이 없으면 기본 이미지 사용
        if (!imageFile.exists()) {
            imageFile = new File("src/main/resources/static/images/common/default-donate-image.png");
        }

        // 이미지 파일을 바이트 배열로 읽기
        byte[] imageBytes = FileCopyUtils.copyToByteArray(imageFile);

        // 응답 반환
        return new ResponseEntity<>(imageBytes, HttpStatus.OK);
    }

    // 기부글 이미지 출력
    @GetMapping("post-image")
    @ResponseBody
    public ResponseEntity<byte[]> getPostImage(@RequestParam("path") String path,
                                                  @RequestParam("name") String name) throws IOException {
        // 이미지 파일 경로 설정
        File imageFile = new File("C:/upload/" + path.replace("/", File.separator) + "/" + name);

        // 파일이 없으면 기본 이미지 사용
        if (!imageFile.exists()) {
            imageFile = new File("src/main/resources/static/images/common/default-book-cover.png");
        }

        // 이미지 파일을 바이트 배열로 읽기
        byte[] imageBytes = FileCopyUtils.copyToByteArray(imageFile);

        // 응답 반환
        return new ResponseEntity<>(imageBytes, HttpStatus.OK);
    }
}
