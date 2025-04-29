package com.app.bookJeog.controller.member;

import com.app.bookJeog.domain.dto.MemberPersonalMemberDTO;
import com.app.bookJeog.domain.dto.PersonalMemberDTO;
import com.app.bookJeog.domain.vo.PersonalMemberVO;
import com.app.bookJeog.service.MemberService;
import com.app.bookJeog.service.MemberServiceImpl;
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
@RequestMapping("/personal/*")
@RequiredArgsConstructor
public class PersonalController {


    private final MemberServiceImpl memberServiceImpl;
    private final MemberService memberService;
    private final PersonalMemberDTO personalMemberDTO;
    private final HttpServletResponse response;
    private final HttpServletRequest request;

    // 개인 마이페이지 조회
    @GetMapping("mypage")
    public String personalMypage() {
        return "member/mypage";
    }


    // 개인 마이페이지 - 내 스크랩(도서 찜)
    @GetMapping("mypage/scrap")
    public String gotoMemberScrap() {
        return "member/scrap-mypage";
    }


    // 개인 마이페이지 - 내 독후감
    @GetMapping("mypage/book-post")
    public String gotoMemberBookPost() {
        return "member/my-post";
    }


    // 개인 마이페이지 - 프로필 수정
    @GetMapping("mypage/profile")
    public String gotoMemberProfile() {
        return "member/my-profile";
    }


    // 개인 마이페이지 - 비밀번호 확인
    @GetMapping("mypage/password-check")
    public String gotoMemberPasswordCheck() {
        return "member/password-check";
    }


    // 개인 마이페이지 - 마일리지 조회
    @GetMapping("mypage/mileage")
    public String gotoMemberMileage() {
        return "member/mileage";
    }


    // 개인 회원탈퇴
    @GetMapping("leave")
    public String gotoMemberLeave() {
        return "member/leave-member";
    }


    // 개인 회원탈퇴 - 탈퇴사유 입력
    @GetMapping("leave/reason")
    public String gotoMemberLeaveReason() {
        return "member/leave-reason";
    }


    // 로그인
    @GetMapping("login")
    public String goToLogin() {

        return "login/login";
    }


    // 로그인 기능
    @PostMapping("/personal/login-check")
    public String loginSuccess(PersonalMemberDTO personalMemberDTO, HttpSession session) {

        PersonalMemberDTO foundMember = memberServiceImpl.loginPersonalMember(personalMemberDTO);

        if(foundMember != null) {
            session.setAttribute("member", foundMember);
            return "redirect:/main/main";
        } else {
            return "redirect:/personal/login?result=fail";
        }
    };


    // 회원가입
    @GetMapping("register-member")
    public String goToRegisterMember(PersonalMemberVO personalMemberVO) {
        return "register/register-member";
    }


    // 이메일 중복검사
    @ResponseBody
    @PostMapping("check-email")
    public Optional<PersonalMemberDTO> checkEmail(@RequestParam String memberEmail) {
        return memberServiceImpl.checkEmail(memberEmail);
    }


    // 회원가입 기능
    @PostMapping("register")
    public String registerMember(MemberPersonalMemberDTO memberPersonalMemberDTO) {
        log.info("memberPersonalMemberDTO: {}", memberPersonalMemberDTO);
        memberServiceImpl.insertPersonalMember(memberPersonalMemberDTO);
        return "redirect:/personal/login";
    }


    // 비밀번호 찾기
    @GetMapping("login/findpasswd-member")
    public String goToFindPasswdMember() {
        return "login/findpasswd-member";
    }


    // 비밀번호 찾기 기능
    @PostMapping("search-my-password")
    public String searchMyPassword(PersonalMemberDTO personalMemberDTO, HttpServletResponse response, HttpSession session ) throws MessagingException {
        Optional<PersonalMemberDTO> foundMember = memberServiceImpl.searchPassword(personalMemberDTO);
        if(foundMember.isPresent()) {
            memberServiceImpl.sendMail(personalMemberDTO.getMemberEmail(), response, session);
            return "redirect:/personal/login/findpasswd-member-certi";
        } else {
        return "redirect:/personal/login/findpasswd-member?result=fail";
        }
    }


    // 비밀번호 찾기 인증페이지
    @GetMapping("login/findpasswd-member-certi")
    public String goToFindPasswdMemberCerti() {
        return "login/findpasswd-member-certi";
    }


    @PostMapping("check")
    public String confirmCode(@CookieValue(name = "token", required = false) String token, String code, HttpServletRequest request) {
        // 쿠키에서 'token'을 가져와 입력받은 코드와 비교

            if(token == null) {
                return "redirect:/personal/login?result=tokken-lose";
            }

        if (token.equals(code)) {
            return "redirect:/personal/change-passwd"; // 비밀번호 재설정 페이지로 리디렉션
        } else {
            return "redirect:/personal/login/check?result=fail";
        }
    }


    // 비밀번호 재설정
    @GetMapping("change-passwd")
    public String goToChangePasswd() {
        return "login/set-member-passwd";
    }

    @PostMapping("member-change-passwd")
    public String changePasswd(String newPasswd, HttpServletRequest request, HttpSession session) {
            session = request.getSession();
            String memberEmail = (String) session.getAttribute("email");

            if(memberEmail != null) {
                memberServiceImpl.changePassword(memberEmail, newPasswd);
                session.invalidate();
                return "redirect:/personal/login";
            }
        return "/personal/login?result=tokken-lose";
    }


    // 카카오 회원가입&로그인
    @GetMapping("/kakao/login")
    public String login(@RequestParam String code, HttpSession session) {
        // 1. 전달받은 인가 코드(code)를 이용해 액세스 토큰 요청
        String token = memberServiceImpl.getKakaoAccessToken(code);

        // 2. 액세스 토큰을 사용해 카카오로부터 사용자 정보 조회
        Optional<PersonalMemberDTO> foundInfo = memberServiceImpl.getKakaoInfo(token);


        // 3. 사용자 정보가 없을 경우 예외 발생
        PersonalMemberDTO personalMemberDTO = foundInfo.orElseThrow(RuntimeException::new);

        // 4. 사용자 이메일로 기존 가입 여부 확인
        Optional<PersonalMemberDTO> foundMember = memberServiceImpl.checkEmail(personalMemberDTO.getMemberEmail());
        log.info("foundMember: {}", foundMember);
        // 5. 가입된 회원이 없으면 회원가입 처리
        if (foundMember.isEmpty()) {
            session.setAttribute("tempMemberInfo", personalMemberDTO);
            return "redirect:/personal/more-info-for-kakao";// 회원가입 로직 실행
        }

        // 6. 세션에 회원 정보 저장 (로그인 상태 유지 목적)
        session.setAttribute("member", foundMember.orElseThrow(RuntimeException::new));
        log.info(session.getAttribute("member").toString());
        // 7. 게시글 목록 페이지로 리다이렉트
        return "redirect:/main/main";
    }


    // 카카오 회원가입 추가 정보입력창
    @GetMapping("more-info-for-kakao")
    public String moreInfoForKakao(Model model, HttpSession session) {
        PersonalMemberDTO tempMemberInfo = (PersonalMemberDTO) session.getAttribute("tempMemberInfo");
        model.addAttribute("tempMemberInfo", tempMemberInfo);
        return "register/register-kakao-member";
    }
    // 카카오 회원가입 완료
    @PostMapping("kakako-register-member")
    public String joinKakaoMember(MemberPersonalMemberDTO memberPersonalMemberDTO) {
        memberServiceImpl.insertPersonalMember(memberPersonalMemberDTO);
        return "redirect:/personal/login";
    };
}