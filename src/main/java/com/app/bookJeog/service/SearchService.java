package com.app.bookJeog.service;

import com.app.bookJeog.domain.dto.BookPostMemberDTO;
import com.app.bookJeog.domain.dto.SponsorMemberProfileDTO;

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
}
