package com.app.bookJeog.controller;

import com.app.bookJeog.controller.exception.ResourceNotFoundException;
import com.app.bookJeog.domain.dto.*;
import com.app.bookJeog.domain.enumeration.MemberType;
import com.app.bookJeog.domain.enumeration.PostType;
import com.app.bookJeog.domain.vo.CommentVO;
import com.app.bookJeog.domain.vo.PostVO;
import com.app.bookJeog.domain.vo.ReceiverLikeVO;
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
import java.util.Arrays;
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
    private final FavoriteService favoriteService;

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
        model.addAttribute("member", session.getAttribute("member"));
        model.addAttribute("sponsorMember", session.getAttribute("sponsorMember"));
        DiscussionPostDTO post = postService.getDiscussionById(id);
        post.setImageUrl(aladinService.getBookCover(post.getBookIsbn()));
        model.addAttribute("postId", id );
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
//        log.info(commentList.toString());
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
                                @RequestParam("file") List<MultipartFile> files, RedirectAttributes redirectAttributes) {
        Long newBookPostId = postService.write(fileBookPostDTO, files);
        redirectAttributes.addFlashAttribute("message", "독후감 작성 완료!");
        return "redirect:/post/bookpost/" + newBookPostId;
    }

    // 독후감 수정
    @GetMapping("bookpost/edit")
    public String goToBookPostEdit() {
        return "post/post-update";
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
        }

        model.addAttribute("DonateCerts",postList);

        return "donation/donate_cert_main";
    }


    // 후원 인증 게시글    
    @GetMapping("donate/post/{postId}")
    public String goTODonateCertPost(@PathVariable Long postId, Model model, HttpSession session){

        List<FileDTO> postFiles = fileService.getDonateCertFilesByPostId(postId);
        for(int i=0 ; i<postFiles.size() ; i++){
            FileDTO fileDTO = postFiles.get(i);
            fileDTO.setId((long) i+1);
        }
        model.addAttribute("files", postFiles);
        model.addAttribute("thumbNail", fileService.getDonateCertFileByPostId(postId));
        model.addAttribute("DonateCert",postService.getDonateCertById(postId));
        model.addAttribute("member", session.getAttribute("member"));
        model.addAttribute("sponsorMember", session.getAttribute("sponsorMember"));
        List<CommentInfoDTO> commentList = new ArrayList<>();
        List<CommentVO> tempList = commentService.getAllCommentByPostId(postId);
        for(CommentVO commentVO : tempList) {
            CommentInfoDTO commentInfoDTO = new CommentInfoDTO();
            CommentDTO commentDTO = commentService.toCommentDTO(commentVO);
            commentInfoDTO.setCommentDTO(commentDTO);
            String memberName = "";
            MemberType memberType = memberService.getById(commentDTO.getMemberId()).getMemberType();
            if(commentService.getMentionedId(commentDTO.getId()) != null){
                Long mentionedId = commentService.getMentionedId(commentDTO.getId());
                commentInfoDTO.setMentionedName(memberService.getMemberName(mentionedId));
            }
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

    @PostMapping("donate/confirm")
    public RedirectView write(@RequestParam String title, @RequestParam String content, @RequestParam(required = false) List<MultipartFile> files, HttpSession session) {
        log.info("첨부 파일 배열 : {}",files);
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


    // 후원 대상 게시글 수정 페이지
    @GetMapping("donate/edit/{postId}")
    public String goToDonateEdit(@PathVariable Long postId, Model model, HttpSession session) {
        model.addAttribute("post", postService.getDonateCertById(postId));
        model.addAttribute("images", fileService.getDonateCertFilesByPostId(postId));
        session.setAttribute("postId", postId);
        return "donation/donate_cert_edit";
    }


    // 후원 인증 게시글 수정
    @PostMapping("donate/edit-confirm")
    public RedirectView editDonatePost(@RequestParam String title,
                                         @RequestParam String content,
                                         @RequestParam(required = false) List<MultipartFile> files,
                                         @RequestParam(value = "remainingImageUrls", required = false) String remainingImageUrls,HttpSession session, Model model) {
        log.info("첨부 파일 배열 : {}",files);
        log.info("기존 파일 배열 : {}",remainingImageUrls);
        List<String> existingImageUrls = (remainingImageUrls != null && !remainingImageUrls.isEmpty())
                ? Arrays.asList(remainingImageUrls.split(","))
                : new ArrayList<>();
        Long postId = (Long) session.getAttribute("postId");
        session.removeAttribute("postId");
        fileService.deleteDonateCertFileByPostId(postId);
        fileService.deleteFile(postId);
        for (String imageUrl : existingImageUrls) {
            // "/image" 부분을 제외하고 실제 경로를 분리
            if (imageUrl.startsWith("/image")) {
                String relativePath = imageUrl.substring(7); // "/image"를 제외한 나머지 경로
                String[] pathParts = relativePath.split("/"); // 경로를 슬래시로 나눔

                String filePath = String.join("/", Arrays.copyOfRange(pathParts, 0, pathParts.length - 1)); // 마지막 부분 제외한 경로
                String fileName = pathParts[pathParts.length - 1]; // 마지막 부분은 파일명

                log.info("분리된 filePath: {}, fileName: {}", filePath, fileName);

                FileDTO fileDTO = new FileDTO();
                fileDTO.setFileName(fileName);
                fileDTO.setFilePath(filePath);
                fileService.insertExistingDonateCertFile(fileDTO, postId);

            }
        }

        DonateCertDTO donateCertDTO = new DonateCertDTO();
        SponsorMemberVO foundMember = (SponsorMemberVO) session.getAttribute("sponsorMember");
        PostDTO postDTO = new PostDTO();
        donateCertDTO.setId(postId);
        donateCertDTO.setDonateCertTitle(title);
        donateCertDTO.setDonateCertText(content);
        postService.updateDonateCertPost(donateCertDTO);

        fileService.uploadDonateCertFiles(postId, files);

        return new RedirectView("/post/donate");

    }
    // 후원 대상 게시판
    @GetMapping("receiver")
    public String goToReceiver(Model model){
        // 기부 도서 조회
        List<BookDonateInfoDTO> donateList = new ArrayList<>();
        List<BookDonateDTO> tempList = postService.getDonateBooks();
//        log.info(tempList.toString());
        for(BookDonateDTO bookDonateDTO : tempList){
            BookDonateInfoDTO donateInfoDTO = new BookDonateInfoDTO();
            donateInfoDTO.setBookDonateDTO(bookDonateDTO);
            donateInfoDTO.setImageUrl(aladinService.getBookCover(bookDonateDTO.getBookIsbn()));
            donateInfoDTO.setAuthor(bookService.getBookByIsbn(bookDonateDTO.getBookIsbn()).get(0).getAuthor());
            donateList.add(donateInfoDTO);
        }
//        log.info(donateList.toString());
        model.addAttribute("donateList", donateList);
        //  게시글 조회
        List<ReceiverPostDTO> receiverPostDTOList = postService.getReceiverPosts();
        for(ReceiverPostDTO receiverPostDTO : receiverPostDTOList){
            receiverPostDTO.setCommentCount(commentService.getAllCommentByPostId(receiverPostDTO.getId()).size());
        }


        model.addAttribute("Posts", receiverPostDTOList);
//        log.info(receiverPostDTOList.toString());
        return "donation/receiver_main";
    }


    // 후원 대상 게시글
    @GetMapping("receiver/post/{postId}")
    public String goToReceiverPost(Model model, @PathVariable Long postId, HttpSession session){
        model.addAttribute("post", postService.getReceiverPostById(postId));
        model.addAttribute("thumbNail", fileService.getReceiverFileByPostId(postId));
        List<FileDTO> postFiles = fileService.getReceiverFilesByPostId(postId);
        for(int i=0 ; i<postFiles.size() ; i++){
            FileDTO fileDTO = postFiles.get(i);
            fileDTO.setId((long) i+1);
        }
        model.addAttribute("files", postFiles);
        model.addAttribute("postId", postId );
        model.addAttribute("member", session.getAttribute("member"));
        model.addAttribute("sponsorMember", session.getAttribute("sponsorMember"));
        List<CommentInfoDTO> commentList = new ArrayList<>();
        List<CommentVO> tempList = commentService.getAllCommentByPostId(postId);
        for(CommentVO commentVO : tempList) {
            CommentInfoDTO commentInfoDTO = new CommentInfoDTO();
            CommentDTO commentDTO = commentService.toCommentDTO(commentVO);
            commentInfoDTO.setCommentDTO(commentDTO);
//            log.info(commentDTO.toString());
            String memberName = "";
            MemberType memberType = memberService.getById(commentDTO.getMemberId()).getMemberType();
            if(commentService.getMentionedId(commentDTO.getId()) != null){
                Long mentionedId = commentService.getMentionedId(commentDTO.getId());
                commentInfoDTO.setMentionedName(memberService.getMemberName(mentionedId));
            }
            switch (memberType) {
                case PERSONAL -> memberName = memberService.getPersonalMember(commentDTO.getMemberId()).getMemberName();

                case SPONSOR -> memberName = memberService.getSponsorMemberById(commentDTO.getMemberId()).getSponsorName();
            }
            commentInfoDTO.setMemberName(memberName);

            log.info(memberName);
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

    @GetMapping("receiver/vote")
    @ResponseBody
    public void voteToReceiver(@RequestParam int point, Model model,HttpSession session) {
        PersonalMemberDTO foundMember = (PersonalMemberDTO) session.getAttribute("member");
        ReceiverLikeDTO receiverLikeDTO = new ReceiverLikeDTO();
        receiverLikeDTO.setMemberId(foundMember.getId());
        receiverLikeDTO.setReceiverLikePoint(point);
        receiverLikeDTO.setReceiverId((Long) model.getAttribute("postId"));
        favoriteService.voteToReceiver(receiverLikeDTO.toVO());
    }


    // 후원 대상 게시글 작성 페이지
    @GetMapping("receiver/write")
    public String goToReceiverWrite(){
        return "donation/receiver_write";
    }

    // 후원 대상 게시글 작성 완료 기능
    @PostMapping("receiver/confirm")
    public RedirectView writeReceiverPost(@RequestParam String title, @RequestParam String content, @RequestParam(required = false) List<MultipartFile> files, HttpSession session) {
        log.info("첨부 파일 배열 : {}",files);
        ReceiverDTO receiverDTO = new ReceiverDTO();
        SponsorMemberVO foundMember = (SponsorMemberVO) session.getAttribute("sponsorMember");
        PostDTO postDTO = new PostDTO();
        postDTO.setPostType(PostType.RECEIVER);
        postDTO.setMemberId(foundMember.getId());
        PostVO postVO = postDTO.toVO();
        postService.insertPost(postVO);
        Long postId = postVO.getId();
        receiverDTO.setId(postId);
        receiverDTO.setReceiverTitle(title);
        receiverDTO.setReceiverText(content);
        log.info(postVO.toString());
        log.info(receiverDTO.toString());
        postService.setReceiverPost(receiverDTO.toVO());
        fileService.uploadReceiverFiles(postId, files);

        return new RedirectView("/post/receiver");

    }

    // 후원 대상 게시글 수정 페이지
    @GetMapping("receiver/edit/{postId}")
    public String goToReceiverEdit(@PathVariable Long postId, Model model, HttpSession session) {
        model.addAttribute("post", postService.getReceiverPostById(postId));
        model.addAttribute("images", fileService.getReceiverFilesByPostId(postId));
        session.setAttribute("postId", postId);
        return "donation/receiver_edit";
    }


    @PostMapping("receiver/edit-confirm")
    public RedirectView editReceiverPost(@RequestParam String title,
                                         @RequestParam String content,
                                         @RequestParam(required = false) List<MultipartFile> files,
                                         @RequestParam(value = "remainingImageUrls", required = false) String remainingImageUrls,HttpSession session, Model model) {
        log.info("첨부 파일 배열 : {}",files);
        log.info("기존 파일 배열 : {}",remainingImageUrls);
        List<String> existingImageUrls = (remainingImageUrls != null && !remainingImageUrls.isEmpty())
                ? Arrays.asList(remainingImageUrls.split(","))
                : new ArrayList<>();
        Long postId = (Long) session.getAttribute("postId");
        session.removeAttribute("postId");
        fileService.deleteReceiverFileByPostId(postId);
        fileService.deleteFile(postId);
        log.info(postId.toString());
        for (String imageUrl : existingImageUrls) {
            // "/image" 부분을 제외하고 실제 경로를 분리
            if (imageUrl.startsWith("/image")) {
                String relativePath = imageUrl.substring(7); // "/image"를 제외한 나머지 경로
                String[] pathParts = relativePath.split("/"); // 경로를 슬래시로 나눔

                String filePath = String.join("/", Arrays.copyOfRange(pathParts, 0, pathParts.length - 1)); // 마지막 부분 제외한 경로
                String fileName = pathParts[pathParts.length - 1]; // 마지막 부분은 파일명

                log.info("분리된 filePath: {}, fileName: {}", filePath, fileName);

                FileDTO fileDTO = new FileDTO();
                fileDTO.setFileName(fileName);
                fileDTO.setFilePath(filePath);
                fileService.insertExistingReceiverFile(fileDTO, postId);

            }
        }

        ReceiverDTO receiverDTO = new ReceiverDTO();
        SponsorMemberVO foundMember = (SponsorMemberVO) session.getAttribute("sponsorMember");
        PostDTO postDTO = new PostDTO();


        receiverDTO.setId(postId);
        receiverDTO.setReceiverTitle(title);
        receiverDTO.setReceiverText(content);
        postService.updateReceiverPost(receiverDTO);

        fileService.uploadReceiverFiles(postId, files);

        return new RedirectView("/post/receiver");

    }



    // 이 주의 기부도서
    @GetMapping("weekly")
    public String goToWeekly(Model model){
        List<BookDonateInfoDTO> donateList = new ArrayList<>();
        List<BookDonateDTO> tempList = postService.getDonateBooks();
//        log.info(tempList.toString());
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
