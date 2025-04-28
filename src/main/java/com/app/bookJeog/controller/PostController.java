package com.app.bookJeog.controller;

import com.app.bookJeog.controller.exception.ResourceNotFoundException;
import com.app.bookJeog.controller.exception.UnauthenticatedException;
import com.app.bookJeog.domain.dto.BookPostDTO;
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

    // 토론게시판 이동
    @GetMapping("discussion")
    public String goToDiscussion() {
        return "discussion/main";
    }


    // 토론 게시글
    @GetMapping("discussion/post")
    public String goToDiscussionPost() {
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
    public String goToDonateCert(){
        return "donation/donate_cert_main";
    }


    // 후원 인증 게시글    
    @GetMapping("donate/post")
    public String goTODonateCertPost(){
        return "donation/donate_cert_post";
    }


    // 후원 인증 게시글 작성
    @GetMapping("donate/write")
    public String goToDonateCertWrite(){
        return "donation/donate_cert_write";
    }


    // 후원 대상 게시판
    @GetMapping("receiver")
    public String goToReceiver(){
        return "donation/receiver_main";
    }


    // 후원 대상 게시글
    @GetMapping("receiver/post")
    public String goToReceiverPost(){
        return "donation/receiver_post";
    }


    // 후원 대상 게시글 작성
    @GetMapping("receiver/write")
    public String goToReceiverWrite(){
        return "donation/receiver_write";
    }


    // 이 주의 기부도서
    @GetMapping("weekly")
    public String goToWeekly(){
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
