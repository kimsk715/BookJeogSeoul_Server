package com.app.bookJeog.controller.member;

import com.app.bookJeog.domain.dto.SponsormemberDTO;
import com.app.bookJeog.domain.vo.SponsorMemberVO;
import com.app.bookJeog.mapper.SponsorMapper;
import com.app.bookJeog.service.SponsorServiceImpl;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@Slf4j
@RequestMapping("/sponsor/*")
@RequiredArgsConstructor
public class SponsorController {


    private final SponsorServiceImpl sponsorServiceImpl;
    private final SponsorMapper sponsorMapper;
    private HttpSession session;

    // 단체 마이페이지 조회
    @GetMapping("mypage")
    public String SponsorMypage() {
        return "organization/mypage";
    }


    // 단체 마이페이지 - 내 기부글
    @GetMapping("mypage/donation")
    public String donationMypage() {
        return "organization/my-donation";
    }


    // 단체 마이페이지 - 비밀번호 확인
    @GetMapping("mypage/password-check")
    public String gotoSponsorPasswordCheck() {
        return "organization/password-check";
    }


    // 단체 마이페이지 - 내 기부 인증글
    @GetMapping("mypage/donate-cert")
    public String donateCertMypage() {
        return "organization/my-donate-post";
    }


    // 단체 마이페이지 - 회원정보 변경
    @GetMapping("mypage/profile")
    public String SponsorMypageProfile() {
        return "organization/my-profile";
    }


    // 단체 회원탈퇴
    @GetMapping("leave")
    public String gotoSponsorLeave() {
        return "organization/leave-organization";
    }


    // 단체 회원탈퇴 - 탈퇴사유 입력
    @GetMapping("leave/reason")
    public String gotoSponsorLeaveReason() {
        return "organization/leave-reason";
    }


    // 단체 로그인
    @GetMapping("login/sponsorship")
    public String goToSponsorship() {
        return "login/sponsorship";
    }

    @PostMapping("login-sponsorship-login")
    public String loginSponsor(SponsormemberDTO sponsorMemberDTO) {
        Optional<SponsorMemberVO> foundMember = sponsorServiceImpl.loginSponsorMember(sponsorMemberDTO);
        if(foundMember.isPresent()) {
            session.setAttribute("sponsorMember", foundMember);
            return "redirect:/main/main";
        } else {
            return "redirect:/sponsor/login/sponsorship?result=fail";
        }
    }


    // 단체 비밀번호 찾기버튼
    @GetMapping("login/findpasswd-sponsor")
    public String goToFindPasswdSponsor() {
        return "login/findpasswd-sponsor";
    }
    // 이메일 버튼을 통해 이동
    @GetMapping("login/findpasswd-sponsor-input")
    public String goToFindPasswdSponsorInput() {
        return "login/findpasswd-sponsor-input";
    }
    @PostMapping("send-mail")
    public String sendMail() {
        return "redirect:/sponsor/input-code";
    }

    // 이메일에서 코드확인
    @GetMapping("input-code")
    public String goToCheckCode() {
        return "login/findpasswd-sponsor-certi";
    }
    @PostMapping("check-sponsor-code")
    public String checkSponsorCode() {
        return "redirect:/sponsor/change-passwd";
    }



    // 비밀번호 재설정
    @GetMapping("change-passwd")
    public String goToChangePasswd() {
        return "login/set-sponsor-passwd";
    }
}


