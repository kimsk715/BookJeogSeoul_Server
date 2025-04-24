package com.app.bookJeog.service;

import com.app.bookJeog.domain.dto.SponsormemberDTO;
import com.app.bookJeog.domain.vo.SponsorMemberVO;
import com.app.bookJeog.repository.MemberDAO;
import com.app.bookJeog.repository.SponsorDAO;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor @Transactional(rollbackFor = Exception.class)
public class SponsorServiceImpl implements SponsorService {

    private final MemberDAO memberDAO;
    private final SponsorDAO sponsorDAO;
    private final HttpSession session;

    // 단체 로그인
    @Override
    public Optional<SponsorMemberVO> loginSponsorMember(SponsormemberDTO sponsorMemberDTO) {
        SponsorMemberVO sponsorMemberVO = toSponsorMemberVO(sponsorMemberDTO);
        return sponsorDAO.findSponsorMember(sponsorMemberVO);
    }


}
