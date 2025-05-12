package com.app.bookJeog.controller;

import com.app.bookJeog.controller.exception.UnauthenticatedException;
import com.app.bookJeog.domain.dto.FollowAlarmDTO;
import com.app.bookJeog.domain.dto.MemberDTO;
import com.app.bookJeog.domain.dto.PersonalMemberDTO;
import com.app.bookJeog.service.AlarmService;
import com.app.bookJeog.service.FavoriteService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@Slf4j
@RequestMapping
@RequiredArgsConstructor
public class FavoriteController {
    private final FavoriteService favoriteService;
    private final AlarmService alarmService;

    // 특정 회원의 스크랩 여부 조회
    @GetMapping("/book/scrap-check")
    @ResponseBody
    public boolean isScrapped(@RequestParam Long isbn){
        return favoriteService.selectMemberScrap(isbn);
    }

    // 특정 책의 스크랩 수 조회
    @GetMapping("/book/scrap-count")
    @ResponseBody
    public int getScrapCount(@RequestParam Long isbn) {
        return favoriteService.getScrapCount(isbn);
    }

    // 스크랩 추가
    @PostMapping("/book/scrap-add")
    @ResponseBody
    public void scrap(@RequestParam Long isbn) {
        favoriteService.scrap(isbn);
    }

    // 스크랩 삭제
    @DeleteMapping("/book/scrap-delete")
    @ResponseBody
    public void scrapDelete(@RequestParam Long isbn) {
        favoriteService.deleteScrap(isbn);
    }

    // 특정 독후감의 좋아요 여부 조회
    @GetMapping("/post/like-check")
    @ResponseBody
    public boolean isLiked(@RequestParam Long bookPostId){
        return favoriteService.isBookPostLiked(bookPostId);
    }

    // 독후감 좋아요 추가
    @PostMapping("/post/like-add")
    @ResponseBody
    public void like(@RequestParam Long bookPostId){
        favoriteService.setBookPostLike(bookPostId);
    }

    // 독후감 좋아요 제거
    @DeleteMapping("/post/like-delete")
    @ResponseBody
    public void likeDelete(@RequestParam Long bookPostId){
        favoriteService.deleteBookPostLike(bookPostId);
    }

    // 내 특정 회원 팔로우 여부 조회
    @GetMapping("/member/follow-check")
    @ResponseBody
    public boolean isFollowed(@RequestParam Long memberId){
        return favoriteService.isMemberFollowed(memberId);
    }

    // 특정 회원 팔로우하기
    @PostMapping("/member/follow-add")
    @ResponseBody
    public void follow(@RequestParam Long memberId, HttpSession session){
        FollowAlarmDTO followAlarmDTO = new FollowAlarmDTO();
        PersonalMemberDTO personalMemberDTO = (PersonalMemberDTO) session.getAttribute("member");
        favoriteService.setMemberFollow(memberId);
        followAlarmDTO.setAlarmSenderId(memberId);
        alarmService.followAlarm(personalMemberDTO.getId(), followAlarmDTO);
    }

    // 특정 회원 팔로우 취소
    @DeleteMapping("/member/follow-delete")
    @ResponseBody
    public void unfollow(@RequestParam Long memberId){
        favoriteService.deleteMemberFollow(memberId);
    }

    // 특정 독후감 좋아요 개수 조회
    @GetMapping("/post/like-count")
    @ResponseBody
    public int getLikeCount(@RequestParam Long bookPostId){
        return favoriteService.countBookPostLike(bookPostId);
    }

    // 특정 독후감에 투표하기
    @GetMapping("/vote/book-post")
    @ResponseBody
    public void voteBookPost(@RequestParam Long bookPostId, HttpSession session){
        PersonalMemberDTO foundMember = (PersonalMemberDTO) session.getAttribute("member");
        if(foundMember != null) {
            Long memberId = foundMember.getId();
            favoriteService.voteBookPost(memberId, bookPostId);
        }
        else{
            throw new RuntimeException();
        }
    }

}
