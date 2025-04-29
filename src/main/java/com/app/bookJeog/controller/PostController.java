package com.app.bookJeog.controller;

import com.app.bookJeog.controller.exception.ResourceNotFoundException;
import com.app.bookJeog.domain.dto.*;
import com.app.bookJeog.domain.enumeration.MemberType;
import com.app.bookJeog.domain.vo.CommentVO;
import com.app.bookJeog.domain.vo.MemberVO;
import com.app.bookJeog.service.*;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;

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


    // 독후감 게시글
    @GetMapping("bookpost/{id}")
    public String goToBookPostPost(@PathVariable Long id, Model model) {
        FileBookPostDTO post = postService.getPostWithFiles(id);
        model.addAttribute("post", post);

        return "post/post-detail";
    }


    // 독후감 작성
    @GetMapping("bookpost/write")
    public String goToBookPostWrite() {
        return "post/post-write";
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
