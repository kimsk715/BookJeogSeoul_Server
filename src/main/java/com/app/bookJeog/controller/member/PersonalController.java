package com.app.bookJeog.controller.member;

import com.app.bookJeog.domain.dto.*;
import com.app.bookJeog.domain.vo.PersonalMemberVO;
import com.app.bookJeog.service.AladinService;
import com.app.bookJeog.service.FavoriteService;
import com.app.bookJeog.service.MemberService;
import com.app.bookJeog.service.MemberServiceImpl;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
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

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    private final FavoriteService favoriteService;
    private final AladinService aladinService;

    // 개인 마이페이지 조회
    @GetMapping("mypage")
    public String personalMypage(HttpSession session, Model model) {
        SponsorMemberDTO sponsorMemberDTO = (SponsorMemberDTO) session.getAttribute("sponsorMember");
        if(sponsorMemberDTO != null) {
            return "redirect:/sponsor/mypage";
        }

        Map<String, Object> myPageData = memberService.getMyPageData(session, model);

        // Map 데이터를 모델에 추가
        model.addAllAttributes(myPageData);
        PersonalMemberDTO member = (PersonalMemberDTO) session.getAttribute("member");
        model.addAttribute("member", member);

        log.info(member.toString());
        return "member/mypage";
    }

    // 남의 마이페이지 조회
    @GetMapping("mypage/{id}")
    public String personalMemberpage(@PathVariable("id") Long memberId, HttpSession session, Model model) {
        model.addAttribute("memberId", memberId);
        PersonalMemberDTO member = (PersonalMemberDTO) session.getAttribute("member");

        if (member != null && member.getId().equals(memberId)) {
            // 내 페이지로 리다이렉트
            return "redirect:/personal/mypage";
        }

        // 남의 페이지 데이터 조회
        Map<String, Object> personalPageData = memberService.getPersonalPageData(memberId, model);
        model.addAllAttributes(personalPageData);

        return "member/member-mypage";
    }

    // 프사 생성 또는 수정
    @PostMapping("/upload-profile")
    @ResponseBody
    public ResponseEntity<String> uploadProfile(@RequestParam("file") MultipartFile file,
                                                HttpSession session) {
        PersonalMemberDTO member = (PersonalMemberDTO) session.getAttribute("member");
        if (member == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인이 필요합니다.");
        }

        memberService.saveOrUpdateProfileImage(file, member.getId());
        return ResponseEntity.ok("프로필 이미지가 저장되었습니다.");
    }

    // 프로필 이미지 삭제
    @DeleteMapping("/delete-profile")
    @ResponseBody
    public ResponseEntity<String> deleteProfile(HttpSession session) {
        PersonalMemberDTO member = (PersonalMemberDTO) session.getAttribute("member");
        if (member == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인이 필요합니다.");
        }

        memberService.deleteProfileImage(member.getId());
        return ResponseEntity.ok("프로필 이미지가 삭제되었습니다.");
    }

    // 닉네임 변경
    @PostMapping("/nickname")
    @ResponseBody
    public ResponseEntity<String> updateNickname(@RequestParam("nickname") String nickname,
                                                 HttpSession session) {
        PersonalMemberDTO member = (PersonalMemberDTO) session.getAttribute("member");
        if (member == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인이 필요합니다.");
        }

        log.info("nickname: " + nickname);
        log.info("member: " + member);
        member.setMemberNickName(nickname); // DTO에 적용
        memberService.updateNickname(member.toVO());
        return ResponseEntity.ok("닉네임이 변경되었습니다.");
    }

    // 비밀번호 변경
    @PostMapping("/password")
    @ResponseBody
    public ResponseEntity<String> updatePassword(@RequestParam("password") String password,
                                                 HttpSession session) {
        PersonalMemberDTO member = (PersonalMemberDTO) session.getAttribute("member");
        if (member == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인이 필요합니다.");
        }

        member.setMemberPassword(password); // DTO에 적용
        memberService.updateMemberPassword(member.toVO());
        return ResponseEntity.ok("비밀번호가 변경되었습니다.");
    }

    // 개인 마이페이지 - 관리 메뉴
    @GetMapping("mypage/settings")
    public String settings(HttpSession session, Model model) {
        PersonalMemberDTO member = (PersonalMemberDTO) session.getAttribute("member");
        if (member == null) {
            return "redirect:/personal/login";
        }
        return "member/member-menu";
    }


    // 개인 마이페이지 - 내 스크랩(도서 찜)
    @GetMapping("mypage/scrap")
    public String gotoMemberScrap(HttpSession session) {
        PersonalMemberDTO member = (PersonalMemberDTO) session.getAttribute("member");
        if (member == null) {
            return "redirect:/personal/login";
        }

        return "member/scrap-mypage";
    }


    // 내 스크랩 데이터
    @GetMapping("/sorted-books")
    @ResponseBody
    public Map<String, Object> getScrappedBooks(
            @RequestParam("offset") int offset,
            @RequestParam("sort") String sort,
            HttpSession session) {

        Long memberId = ((PersonalMemberDTO) session.getAttribute("member")).getId();
        String orderType = "created-at".equals(sort) ? "latest" : "scrap";

        List<Long> isbnList = favoriteService.getScrappedIsbnList(memberId, offset, orderType);

        List<AladinBookDTO> books = "created-at".equals(sort)
                ? aladinService.getBooksByIsbnList(isbnList)
                : aladinService.getSortedBooksByIsbnList(isbnList, sort);

        Map<String, Object> response = new HashMap<>();
        response.put("books", books);
        response.put("hasMore", isbnList.size() == 18);

        return response;
    }

    // 개인 마이페이지 - 내 독후감
    @GetMapping("mypage/book-post")
    public String gotoMemberBookPost(HttpSession session, Model model) {
        PersonalMemberDTO member = (PersonalMemberDTO) session.getAttribute("member");
        if (member == null) {
            return "redirect:/personal/login";
        }
        Long memberId = member.getId();
        int postCount = memberService.findMyBookPostCount(memberId);
        model.addAttribute("postCount", postCount);
        return "member/my-post";
    }

    // 개인 마이페이지 - 프로필 수정
    @GetMapping("mypage/profile")
    public String gotoMemberProfile(HttpSession session, Model model) {
        PersonalMemberDTO member = memberService.getCurrentMember(session);
        if (member == null) {
            return "redirect:/personal/login";
        }

        model.addAttribute("member", member);
        model.addAttribute("profileUrl", memberService.getProfileImageUrl(member.getId()));

        return "member/my-profile";
    }

    // 개인 마이페이지 - 프로필 수정(카카오)
    @GetMapping("mypage/profile-kakao")
    public String gotoKakaoMemberProfile(HttpSession session, Model model) {
        PersonalMemberDTO member = memberService.getCurrentMember(session);

        if (member == null) {
            // 로그인하지 않은 경우 에러 페이지로 리다이렉트하거나 기본값 설정
            return "redirect:/personal/login";
        }

        model.addAttribute("member", member);
        model.addAttribute("profileUrl", memberService.getProfileImageUrl(member.getId()));

        return "member/my-profile-kakao";
    }


    // 개인 마이페이지 - 비밀번호 확인
    @GetMapping("mypage/password-check")
    public String gotoMemberPasswordCheck( HttpSession session) {
        PersonalMemberDTO member = (PersonalMemberDTO) session.getAttribute("member");
        if (member == null) {
            return "redirect:/personal/login";
        }

        // 비밀번호가 없으면 카카오 회원이므로 바로 프로필 편집으로 이동
        if (member.getMemberPassword() == null) {
            return "redirect:/personal/mypage/profile-kakao";
        }

        return "member/password-check";
    }

    @PostMapping("/password-check")
    public String checkPassword(@RequestParam("password") String password,
                                HttpSession session,
                                RedirectAttributes redirect) {
        PersonalMemberDTO member = (PersonalMemberDTO) session.getAttribute("member");

        if (member == null) {
            return "redirect:/personal/login";
        }

        boolean isCorrect = memberService.checkPassword(member.getId(), password);

        if (isCorrect) {
            return "redirect:/personal/mypage/profile";
        } else {
            redirect.addFlashAttribute("error", "비밀번호가 일치하지 않습니다.");
            return "redirect:/personal/mypage/password-check";
        }
    }


    // 개인 마이페이지 - 마일리지 조회
    @GetMapping("mypage/mileage")
    public String gotoMemberMileage(HttpSession session, Model model) {
        PersonalMemberDTO member = (PersonalMemberDTO) session.getAttribute("member");

        if (member == null) {
            return "redirect:/personal/login";
        }

        Long memberId = member.getId();
        int mileage = memberService.findMyMileage(memberId);
        model.addAttribute("mileage", mileage);

        return "member/mileage";
    }


    // 개인 회원탈퇴
    @GetMapping("leave")
    public String gotoMemberLeave(HttpSession session, Model model) {
        PersonalMemberDTO member = (PersonalMemberDTO) session.getAttribute("member");
        if (member == null) {
            return "redirect:/personal/login";
        }
        Long memberId = member.getId();

        // 회원 정보 조회
        Map<String, Object> memberInfo = memberService.findMyActivities(memberId);

        // Map 생성
        memberInfo.put("memberId", memberId);
        memberInfo.put("nickname", member.getMemberNickName());

        // Model에 추가
        log.info("memberInfo: " + memberInfo);
        model.addAttribute("memberInfo", memberInfo);
        return "member/leave-member";
    }

    @PostMapping("/leave/confirm")
    public String leaveMember(@RequestParam("memberId") Long memberId, HttpSession session, RedirectAttributes redirectAttributes) {
        // 회원 상태 업데이트 (탈퇴 처리)
        memberService.updateDeletedMemberStatus(memberId);

        // 세션 무효화
        session.invalidate();

        // Flash Attribute로 메시지 전달
        redirectAttributes.addFlashAttribute("message", "탈퇴가 완료되었습니다.");

        return "redirect:/";
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
    public String goToRegisterMember() {
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


    @GetMapping("logout")
    public String logout(HttpSession session) {
        session.removeAttribute("member");
        session.removeAttribute("sponsorMember");

        return "redirect:/personal/login";
    }
    // 내 프사
    @GetMapping("profile")
    @ResponseBody
    public ResponseEntity<byte[]> getProfileImage(@RequestParam("path") String path,
                                                  @RequestParam("name") String name) throws IOException {
        // 이미지 파일 경로 설정
        File imageFile = new File("C:/upload/" + path.replace("/", File.separator) + "/" + name);

        // 파일이 없으면 기본 이미지 사용
        if (!imageFile.exists()) {
            imageFile = new File("src/main/resources/static/images/common/user_profile_example.png");
        }

        // 이미지 파일을 바이트 배열로 읽기
        byte[] imageBytes = FileCopyUtils.copyToByteArray(imageFile);
        log.info("📷 이미지 path:", path, "파일명:", name);

        // 응답 반환
        return new ResponseEntity<>(imageBytes, HttpStatus.OK);

    }
}