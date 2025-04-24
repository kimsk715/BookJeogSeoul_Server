package com.app.bookJeog.controller;

import com.app.bookJeog.controller.exception.ResourceNotFoundException;
import com.app.bookJeog.domain.dto.BookPostDTO;
import com.app.bookJeog.domain.dto.BookPostMemberDTO;
import com.app.bookJeog.domain.dto.FileBookPostDTO;
import com.app.bookJeog.service.PostService;
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
