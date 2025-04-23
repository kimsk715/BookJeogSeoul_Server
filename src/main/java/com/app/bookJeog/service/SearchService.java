package com.app.bookJeog.service;

import com.app.bookJeog.domain.dto.BookPostMemberDTO;
import com.app.bookJeog.domain.dto.DiscussionPostDTO;
import com.app.bookJeog.domain.dto.ReceiverPostDTO;
import com.app.bookJeog.domain.dto.SponsorMemberProfileDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface SearchService {

    // 알라딘 api로 도서검색
    public Map<String, Object> searchBooksByKeyword(String keyword);

    // 검색한 검색어에 맞는 독후감 통합검색 조회
    List<BookPostMemberDTO> searchBookPosts(String keyword);

    // 검색한 검색어에 맞는 독후감 무한스크롤로 출력
    public Map<String, Object> getAllBooksWithCount(String keyword, int offset, String sortType);

    // 기업회원 통합검색 조회
    List<SponsorMemberProfileDTO> findSponsorMembersWithProfile(String keyword);

    // 기업회원 검색 결과 개수 조회
    int findSponsorMembersTotal(String keyword);

    // 기업회원 전체페이지 조회(무한스크롤)
    List<SponsorMemberProfileDTO> findAllSponsorMembers(String keyword, int offset, String sortType);

    // 검색한 검색어에 맞는 토론글 통합검색 조회
    List<DiscussionPostDTO> searchDiscussions(String keyword);

    // 검색한 검색어에 맞는 토론글 통합검색 개수 조회
    int findAllDiscussionCount(String keyword);

    // 검색한 검색어에 맞는 토론글 무한스크롤로 출력
    List<DiscussionPostDTO> findAllDiscussion(String keyword, int offset, String sortType);

    // 검색한 검색어에 맞는 기부글 통합검색 조회
    public List<ReceiverPostDTO> searchReceivers(String keyword);

    // 검색한 검색어에 맞는 기부글 통합검색 개수 조회
    public int findAllReceiverCount(String keyword);

    // 검색한 검색어에 맞는 기부글 무한스크롤로 출력
    public List<ReceiverPostDTO> findAllReceivers(String keyword, int offset, String sortType);
}
