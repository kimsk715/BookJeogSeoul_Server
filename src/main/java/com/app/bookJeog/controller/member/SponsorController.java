package com.app.bookJeog.controller.member;

import com.app.bookJeog.domain.dto.PersonalMemberDTO;

import com.app.bookJeog.domain.dto.PersonalMemberDTO;

import com.app.bookJeog.domain.dto.SponsorMemberDTO;
import com.app.bookJeog.domain.dto.SponsorPostDTO;
import com.app.bookJeog.domain.vo.SponsorMemberVO;
import com.app.bookJeog.mapper.SponsorMapper;
import com.app.bookJeog.service.SponsorService;
import com.app.bookJeog.service.SponsorServiceImpl;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@Slf4j
@RequestMapping("/sponsor/*")
@RequiredArgsConstructor
public class SponsorController {


    private final SponsorServiceImpl sponsorServiceImpl;
    private final SponsorService sponsorService;
    private final SponsorMapper sponsorMapper;
    private final SponsorMemberDTO sponsormemberDTO;
    private final SponsorMemberDTO sponsorMemberDTO;
    private HttpSession session;

    // 단체 마이페이지 조회
    @GetMapping("/mypage/{id}")
    public String getSponsorMypage(@PathVariable("id") Long sponsorId, Model model) {
        SponsorPostDTO sponsor = sponsorService.getSponsorMypage(sponsorId);
        model.addAttribute("sponsor", sponsor);
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
    public String loginSponsor(SponsorMemberDTO sponsorMemberDTO, HttpSession session) {
        Optional<SponsorMemberDTO> foundMember = sponsorServiceImpl.loginSponsorMember(sponsorMemberDTO);
        if(foundMember.isPresent()) {
            session.setAttribute("sponsorMember", foundMember.orElseThrow(RuntimeException::new));
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
    public String sendMail(SponsorMemberDTO sponsormemberDTO, HttpServletResponse response, HttpSession session) throws MessagingException {
        Optional<SponsorMemberVO> foundEmail = sponsorServiceImpl.selectEmailForPassword(sponsormemberDTO);
            if(foundEmail.isPresent()) {
                sponsorServiceImpl.sendMail(sponsormemberDTO, response, session);
                return "redirect:/sponsor/input-code";
            }
        return "redirect:/sponsor/send-mail?result=fail";
    }



    // 이메일에서 코드확인
    @GetMapping("input-code")
    public String goToCheckCode() {
        return "login/findpasswd-sponsor-certi";
    }
    @PostMapping("check-sponsor-code")
    public String checkSponsorCode(@CookieValue(name = "token", required = false) String token, String code, HttpServletRequest request) {

        if(token == null) {
            return "redirect:/personal/login?result=tokken-lose";
        }
        if (token.equals(code)) {
            return "redirect:/sponsor/change-passwd"; // 비밀번호 재설정 페이지로 리디렉션
        } else {
            return "redirect:/sponsor/input-code?result=fail";
        }
    }



    // 비밀번호 재설정
    @GetMapping("change-passwd")
    public String goToChangePasswd() {
        return "login/set-sponsor-passwd";
    }

    @PostMapping("sponsor-change-passwd")
    public String changePasswd (String newPasswd, HttpServletRequest request, HttpSession session){
        session = request.getSession();
        sponsorMemberDTO.setSponsorEmail((String) session.getAttribute("email"));
        String memberEmail = (String) session.getAttribute("email");

        if(memberEmail != null) {
            sponsorServiceImpl.changePassword(sponsorMemberDTO, newPasswd);
            session.invalidate();
            return "redirect:/personal/login";
        }
        return "/personal/login?result=tokken-lose";}

}


