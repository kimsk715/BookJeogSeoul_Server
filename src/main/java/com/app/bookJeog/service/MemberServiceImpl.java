package com.app.bookJeog.service;


import com.app.bookJeog.domain.dto.*;
import com.app.bookJeog.domain.vo.AdminVO;
import com.app.bookJeog.domain.vo.MemberVO;
import com.app.bookJeog.domain.vo.PersonalMemberVO;
import com.app.bookJeog.domain.vo.SponsorMemberVO;
import com.app.bookJeog.mapper.MemberMapper;
import com.app.bookJeog.repository.MemberDAO;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Optional;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor @Transactional(rollbackFor = Exception.class)
@Slf4j
public class MemberServiceImpl implements MemberService {
    private final MemberDAO memberDAO;
    private final MemberMapper memberMapper;
    private final PersonalMemberDTO personalMemberDTO;
    private final HttpSession session;
    private final PersonalMemberVO personalMemberVO;
    private final JavaMailSender javaMailSender;


    @Override
    public List<PersonalMemberVO> getAllPersonal(Pagination pagination) {
        return memberDAO.findAllPersonal(pagination);
    }

    @Override
    public int countAllPersonal(Pagination pagination) {
        return memberDAO.countAllPersonal(pagination);
    }


    // 이메일 중복검사
    @Override
    public Optional<PersonalMemberVO> checkEmail(String email) {
        personalMemberDTO.setMemberEmail(email);
        PersonalMemberVO personalMemberVO = toPersonalMemberVO(personalMemberDTO);
        return memberDAO.findByEmail(personalMemberVO);
    }

    @Override
    public SponsorMemberVO getSponsorMemberById(Long id) {
        return memberDAO.findSponsorMemberById(id);
    }

    // 회원 타입에 따라 상태 변경(개인 회원이면 개인 회원 테이블에 영향
    @Override
    public void updateMemberStatus(Long memberId) {
        if(memberDAO.findById(memberId).getMemberType().getCode().equals("개인")){
            memberDAO.updatePersonalMemberStatus(memberId);
        }
        else{
            memberDAO.updateSponsorMemberStatus(memberId);
        }
    }

    @Override
    public MemberVO getById(Long id) {
        return memberDAO.findById(id);
    }

    @Override
    public PersonalMemberVO getPersonalMember(Long memberId) {
        return memberDAO.findPersonalMemberById(memberId);
    }

    @Override
    public List<SponsorMemberDTO> getAllSponsor(Pagination pagination) {
        List<SponsorMemberDTO> sponsorMemberDTOList = new ArrayList<>();
        List<SponsorMemberVO> tempList = memberDAO.findAllSponsor(pagination);
        for(SponsorMemberVO sponsorMemberVO : tempList){
            SponsorMemberDTO sponsorMemberDTO = new SponsorMemberDTO();
            sponsorMemberDTO.setId(sponsorMemberVO.getId());
            sponsorMemberDTO.setSponsorId(sponsorMemberVO.getSponsorId());
            sponsorMemberDTO.setSponsorPassword(sponsorMemberVO.getSponsorPassword());
            sponsorMemberDTO.setSponsorName(sponsorMemberVO.getSponsorName());
            sponsorMemberDTO.setSponsorEmail(sponsorMemberVO.getSponsorEmail());
            sponsorMemberDTO.setSponsorPhoneNumber(sponsorMemberVO.getSponsorPhoneNumber());
            sponsorMemberDTO.setSponsorMainAddress(sponsorMemberVO.getSponsorMainAddress());
            sponsorMemberDTO.setSponsorSubAddress(sponsorMemberVO.getSponsorSubAddress());
            sponsorMemberDTO.setSponsorMemberStatus(sponsorMemberVO.getSponsorMemberStatus());
            sponsorMemberDTO.setCreatedDate(sponsorMemberVO.getCreatedDate());
            sponsorMemberDTO.setUpdatedDate(sponsorMemberVO.getUpdatedDate());
            sponsorMemberDTOList.add(sponsorMemberDTO);
        }

        return sponsorMemberDTOList;
    }

    @Override
    public int countAllSponsor(Pagination pagination) {
        return memberDAO.countAllSponsor(pagination);
    }

    @Override
    public void insertSponsorMember(SponsorMemberDTO sponsorMemberDTO) {
        MemberVO memberVO = toSponsorMember();
        memberDAO.setCommonMember(memberVO);
        sponsorMemberDTO.setId(memberVO.getId());
        memberDAO.insertSponsorMember(sponsorMemberDTO.toVO());
    }

    @Override
    public List<AdminDTO> getAllAdmin(Pagination pagination) {
        List<AdminDTO> adminDTOList = new ArrayList<>();
        List<AdminVO> tempList = memberDAO.findAllAdmin(pagination);
        for(AdminVO adminVO : tempList){
            AdminDTO adminDTO = new AdminDTO();
            adminDTO.setId(adminVO.getId());
            adminDTO.setAdminId(adminVO.getAdminId());
            adminDTO.setAdminPassword(adminVO.getAdminPassword());
            adminDTO.setAdminDepartment(adminVO.getAdminDepartment());
            adminDTO.setAdminGrade(adminVO.getAdminGrade());
            adminDTO.setAdminName(adminVO.getAdminName());
            adminDTO.setAdminMemberStatus(adminVO.getAdminMemberStatus());
            adminDTO.setCreatedDate(adminVO.getCreatedDate());
            adminDTO.setUpdatedDate(adminVO.getUpdatedDate());
            adminDTOList.add(adminDTO);
        }

        return adminDTOList;
    }

    @Override
    public int countAllAdmin(Pagination pagination) {
        return memberDAO.countAllAdmin(pagination);
    }

    @Override
    public void insertAdmin(AdminDTO adminDTO) {
        MemberVO memberVO = toAdmin();
        memberDAO.setCommonMember(memberVO);
        log.info(memberVO.toString());
        adminDTO.setId(memberVO.getId());
        log.info(adminDTO.toString());
        memberDAO.setAdmin(adminDTO.toVO());
    }


    // 닉네임 중복검사 만들어야함
    //일반회원 회원가입
    public void insertPersonalMember(MemberPersonalMemberDTO memberPersonalMemberDTO) {
        MemberVO memberVO = toMemberVO();
        memberDAO.setCommonMember(memberVO);
        memberPersonalMemberDTO.setId(memberVO.getId());
        PersonalMemberVO personalMemberVO = toPersonalMemberVO(memberPersonalMemberDTO);
        personalMemberVO = toPersonalMemberVO(memberPersonalMemberDTO);
        memberDAO.setPersonalMember(personalMemberVO);
    }


    // 로그인
    public PersonalMemberDTO loginPersonalMember(PersonalMemberDTO personalMemberDTO) {

        PersonalMemberVO personalMemberVO = toPersonalMemberVO(personalMemberDTO);
        Optional<PersonalMemberVO> foundMember = memberDAO.findPersonalMember(personalMemberVO);


        // 로그인 실패 시 null 반환
        if (foundMember == null) {
            return null;
        }

        // VO를 DTO로 다시 변환 후 반환
        return foundMember.map(this::toPersonalMemberDTO).orElse(null);
    }


    // 비밀번호 찾기에서 먼저 등록되어있는지 확인
    public Optional<PersonalMemberDTO> searchPassword (PersonalMemberDTO personalMemberDTO) {

        PersonalMemberVO personalMemberVO = toPersonalMemberVO(personalMemberDTO);

        Optional<PersonalMemberVO> foundMember = memberDAO.findPersonalMemberPassword(personalMemberVO);

        return foundMember.map(this::toPersonalMemberDTO);
    }


    // 비밀번호 찾기에서 등록되어있다면 인증메일발송
    public void sendMail(String email, HttpServletResponse response, HttpSession session) throws MessagingException {
        String code = createCode();

        // 1. 쿠키로 인증 코드 저장
        Cookie cookie = new Cookie("token", code);
        cookie.setMaxAge(60 * 5); // 30분 유효
        cookie.setPath("/");
        response.addCookie(cookie);

        // 1-2 세션에 이메일 담음
       session.setAttribute("email", email);

        // 2. 메일 내용 구성
        String receiver = email; // 사용자 이메일로 전송
        String sender = "rksel0712@gmail.com";
        String title = "북적북적에서 비밀번호변경!";

        StringBuilder body = new StringBuilder();

        body.append("<body>");
        body.append("<div style=\"width: 600px; margin: 0 auto; background-color: #f8f9fa; border: 1px solid #ddd; border-radius: 8px; padding: 20px;\">");
        body.append("<div style=\"background-color: #fff; border: 1px solid #ccc; width: 70%; margin: 30px auto; padding: 60px 30px; text-align: center; border-radius: 8px;\">");
        body.append("<img src='cid:logoImage' alt=\"로고 이미지\" style=\"max-width: 100px; margin-bottom: 20px;\" />");
        body.append("<h2 style=\"margin: 40px 0 20px; font-size: 40px; font-weight: 400; letter-spacing: -2px; color: #333;\">");
        body.append("비밀번호 찾기");
        body.append("</h2>");
        body.append("<p style=\"font-size: 14px; line-height: 22px; color: #555; margin-bottom: 20px;\">");
        body.append("본 메일은 비밀번호 변경을 위해<br />발송되는 메일입니다.");
        body.append("</p>");
        body.append("<p style=\"font-size: 18px; line-height: 24px; color: #000; font-weight: bold; background-color: #f0f0f0; display: inline-block; padding: 10px 20px; border: 1px solid #ddd; border-radius: 5px;\">");
        body.append('"'+code+'"');
        body.append("</p>");
        body.append("<div style=\"margin-top: 40px;\">");
        body.append("<p style=\"font-size: 11px; line-height: 14px; color: #888;\">");
        body.append("본 이메일은 발송된 지 5분이 지나면 만료됩니다.<br />");
        body.append("문의사항은 <a href=\"mailto:rksel0712@gmail.com\" style=\"color: #007bff; text-decoration: none;\">rksel0712@gmail.com</a>으로 보내주세요.");
        body.append("</p>");
        body.append("</div>");
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


    // 비밀번호 변경하기
    public void changePassword(String memberEmail, String newPasswd) {
        memberMapper.updatePassword(memberEmail, newPasswd);
    }


    // 카카오 accces토큰 가져오기
    public  String getKakaoAccessToken(String code){
        String accessToken = null;
        String requestURI = "https://kauth.kakao.com/oauth/token";

        try {
            URL url = new URL(requestURI);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            StringBuilder stringBuilder = new StringBuilder();
            BufferedWriter bufferedWriter = null;
            String redirectURI = "http://localhost:10000/personal/kakao/login";

            connection.setRequestMethod("POST");
            connection.setDoOutput(true);

            stringBuilder.append("grant_type=authorization_code");
            stringBuilder.append("&client_id=c3aac24d973a36402998e7772742f80c");
            stringBuilder.append("&redirect_uri=" + redirectURI);
            stringBuilder.append("&code=" + code);

            bufferedWriter = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream()));
            bufferedWriter.write(stringBuilder.toString());
            bufferedWriter.close();

            if(connection.getResponseCode() == 200){
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line = null;
                String result = "";

                while((line = bufferedReader.readLine()) != null){
                    result += line;
                }

                JsonElement jsonElement = JsonParser.parseString(result);
                accessToken = jsonElement.getAsJsonObject().get("access_token").getAsString();

                bufferedReader.close();
            }

        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return accessToken;
    }



    // 카카오 info 가져오기
        public Optional<PersonalMemberDTO> getKakaoInfo(String token) {
            String accessToken = null;
            String requestURI = "https://kapi.kakao.com/v2/user/me";
            PersonalMemberDTO personalMemberDTO = null;

            try {
                // URL 객체 생성
                URL url = new URL(requestURI);

                // HTTP 연결 생성
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                // 요청 방식 설정: POST
                connection.setRequestMethod("POST");
                connection.setDoOutput(true); // output 사용 가능하도록 설정

                // Authorization 헤더에 Bearer 타입의 토큰 설정
                connection.setRequestProperty("Authorization", "Bearer " + token);

                // 응답 코드가 200 OK일 때만 응답 처리
                if (connection.getResponseCode() == 200) {
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    String line;
                    String result = "";

                    // 응답 JSON 문자열을 한 줄씩 읽어서 합침
                    while ((line = bufferedReader.readLine()) != null) {
                        result += line;
                    }

                    // 응답 JSON 문자열 파싱
                    JsonElement jsonElement = JsonParser.parseString(result);

                    // "kakao_account" 항목 추출
                    JsonElement kakaoAccount = jsonElement.getAsJsonObject().get("kakao_account").getAsJsonObject();

                    // 그 안의 "profile" 항목 추출
                    JsonElement profile = kakaoAccount.getAsJsonObject().get("profile");

                    // DTO 객체 생성 및 값 주입
                    personalMemberDTO = new PersonalMemberDTO();
                    personalMemberDTO.setMemberEmail(kakaoAccount.getAsJsonObject().get("email").getAsString()); // 이메일 추출
                    log.info("personalMemberDTO {}", personalMemberDTO);
                    personalMemberDTO.setMemberName(profile.getAsJsonObject().get("nickname").getAsString());    // 닉네임 추출
                    log.info("personalMemberDTO {}", personalMemberDTO);

                    bufferedReader.close();
                }

            } catch (MalformedURLException e) {
                // URL 형식 오류
                throw new RuntimeException("잘못된 URL입니다: " + e.getMessage(), e);
            } catch (IOException e) {
                // 입출력 에러 처리
                throw new RuntimeException("연결 중 오류가 발생했습니다: " + e.getMessage(), e);
            }
            return Optional.ofNullable(personalMemberDTO);
        };
}
