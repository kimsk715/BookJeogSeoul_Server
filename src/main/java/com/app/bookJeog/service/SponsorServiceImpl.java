package com.app.bookJeog.service;

import com.app.bookJeog.domain.dto.PersonalMemberDTO;
import com.app.bookJeog.domain.dto.SponsorMemberDTO;

import com.app.bookJeog.domain.vo.PersonalMemberVO;
import com.app.bookJeog.domain.vo.SponsorMemberVO;
import com.app.bookJeog.mapper.MemberMapper;
import com.app.bookJeog.repository.MemberDAO;
import com.app.bookJeog.repository.SponsorDAO;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor @Transactional(rollbackFor = Exception.class)
public class SponsorServiceImpl implements SponsorService {

    private final MemberDAO memberDAO;
    private final SponsorDAO sponsorDAO;
    private final HttpSession session;
    private final MemberMapper memberMapper;
    private final PersonalMemberDTO personalMemberDTO;
    private final PersonalMemberVO personalMemberVO;
    private final JavaMailSender javaMailSender;
    // 단체 로그인
    @Override
    public Optional<SponsorMemberVO> loginSponsorMember(SponsorMemberDTO sponsorMemberDTO) {
        SponsorMemberVO sponsorMemberVO = toSponsorMemberVO(sponsorMemberDTO);
        return sponsorDAO.findSponsorMember(sponsorMemberVO);
    }

    @Override
    public void changePassword(SponsorMemberDTO sponsorMemberDTO, String newPasswd) {
        SponsorMemberVO sponsorMemberVO = toSponsorMemberVO(sponsorMemberDTO);
        sponsorDAO.findSponsorMemberEmail(sponsorMemberVO);
    }


    // 이메일 중복검사
    @Override
    public Optional<SponsorMemberVO> selectEmailForPassword(SponsorMemberDTO sponsorMemberDTO) {
        SponsorMemberVO sponsorMemberVO = toSponsorMemberVO(sponsorMemberDTO);
        return sponsorDAO.findSponsorMemberEmail(sponsorMemberVO);
    }


    // 이메일 인증 발송
    public void sendMail(SponsorMemberDTO sponsormemberDTO, HttpServletResponse response, HttpSession session) throws MessagingException {
        String code = createCode();

        // 1. 쿠키로 인증 코드 저장
        Cookie cookie = new Cookie("token", code);
        cookie.setMaxAge(60 * 5); // 30분 유효
        cookie.setPath("/");
        response.addCookie(cookie);

        // 1-2 세션에 이메일 담음
        session.setAttribute("email", sponsormemberDTO.getSponsorEmail());

        // 2. 메일 내용 구성
        String receiver = sponsormemberDTO.getSponsorEmail(); // 사용자 이메일로 전송
        String sender = "rksel0712@gmail.com";
        String title = "북적북적에서 비밀번호변경!";

        StringBuilder body = new StringBuilder();

        body.append("<body style=\"margin: 0; padding: 0; font-family: 'Apple SD Gothic Neo', 'Malgun Gothic', sans-serif; background-color: #e9f5ee;\">");
        body.append("<div style=\"max-width: 600px; margin: 40px auto; background-color: #ffffff; border: 1px solid #d4e8dc; border-radius: 10px; padding: 30px; box-shadow: 0 4px 12px rgba(0,0,0,0.05);\">");

        body.append("<div style=\"text-align: center;\">");
        body.append("<img src='cid:logoImage' alt=\"로고 이미지\" style=\"max-width: 80px; margin-bottom: 25px;\" />");
        body.append("<h2 style=\"color: #2e7d32; font-size: 32px; margin-bottom: 15px; letter-spacing: -1px;\">비밀번호 찾기</h2>");
        body.append("<p style=\"font-size: 15px; line-height: 24px; color: #444;\">");
        body.append("비밀번호 재설정을 위해 아래 인증코드를 입력해주세요.<br />본 메일은 인증을 위해 발송된 것입니다.");
        body.append("</p>");

        body.append("<p style=\"font-size: 20px; font-weight: bold; color: #2e7d32; background-color: #e8f5e9; border: 1px dashed #a5d6a7; display: inline-block; padding: 12px 24px; border-radius: 6px; margin: 30px 0;\">");
        body.append(code);
        body.append("</p>");

        body.append("<p style=\"font-size: 12px; color: #888; margin-top: 30px; line-height: 18px;\">");
        body.append("본 인증코드는 발송 시점으로부터 <strong>5분간 유효</strong>합니다.<br />");
        body.append("문의사항이 있을 경우 <a href=\"mailto:rksel0712@gmail.com\" style=\"color: #388e3c; text-decoration: underline;\">rksel0712@gmail.com</a>으로 연락해주세요.");
        body.append("</p>");
        body.append("</div>");

        body.append("</div>");
        body.append("</body>");



        String emailBody = body.toString();

        // 3. 메일 발송 설정
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");

        helper.setFrom(sender);
        helper.setTo(receiver);
        helper.setSubject(title);
        helper.setText(body.toString(), true);

        FileSystemResource fileSystemResource = new FileSystemResource(new File("src/main/resources/static/images/common/Logo.png"));
        helper.addInline("logoImage", fileSystemResource);

        // 4. 메일 발송
        javaMailSender.send(mimeMessage);
    }
    public String createCode() {
        String codes = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String code = "";
        Random random = new Random();

        for(int i=0; i<5; i++) {
            code += codes.charAt(random.nextInt(codes.length()));
        }
        return code;
    }


}
