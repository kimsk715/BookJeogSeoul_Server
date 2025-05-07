package com.app.bookJeog.service;


import com.app.bookJeog.controller.exception.UnauthenticatedException;
import com.app.bookJeog.domain.dto.*;
import com.app.bookJeog.domain.enumeration.MemberType;
import com.app.bookJeog.domain.vo.*;
import com.app.bookJeog.mapper.MemberMapper;
import com.app.bookJeog.repository.FavoriteDAO;
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
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.stream.Collectors;

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
    private final FavoriteDAO favoriteDAO;
    private final AladinService aladinService;



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
    public Optional<PersonalMemberDTO> checkEmail(String email) {
        Optional<PersonalMemberVO> memberVO = memberDAO.findByEmail(email);
       PersonalMemberVO foundMemberVO = memberVO.orElseThrow(() -> new RuntimeException("Email not found"));
        Optional<PersonalMemberDTO> foundMemberDTO = Optional.ofNullable(toPersonalMemberDTO(foundMemberVO));
        return foundMemberDTO;
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

    @Override
    public MemberType getMemberType(Long memberId) {
        MemberVO memberVO = memberDAO.findById(memberId);
        return memberVO.getMemberType();
    }

    @Override
    public String getMemberName(Long memberId) {
        MemberVO memberVO = memberDAO.findById(memberId);
        String memberName = "";
        if(memberVO.getMemberType().equals(MemberType.PERSONAL)){
            memberName = memberDAO.findPersonalMemberById(memberId).getMemberName();
        }
        else if(memberVO.getMemberType().equals(MemberType.SPONSOR)){
            memberName = memberDAO.findSponsorMemberById(memberId).getSponsorName();
        }
        return memberName;
    }

    // 독후감 많이쓴 사람 조회
    @Override
    public List<PersonalMemberDTO> selectTopBookPostMember() {
        List<PersonalMemberVO> temp = memberDAO.findTopBookPostMember();
        List<PersonalMemberDTO> personalMemberDTOS = new ArrayList<>();
        for(PersonalMemberVO personalMemberVO : temp){
            PersonalMemberDTO personalMemberDTO = toPersonalMemberDTO(personalMemberVO);
            personalMemberDTOS.add(personalMemberDTO);
        }



        return personalMemberDTOS;
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


                    PersonalMemberVO personalMemberVO = toPersonalMemberVO(personalMemberDTO);
                    Optional<PersonalMemberVO> foundMember = memberDAO.findPersonalMember(personalMemberVO);


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



    // 독후감 많이쓴 사람
    public List<PersonalMemberPostMemberProfileDTO> selectTopBookPostMemberProfile() {
        return memberDAO.findMemberInfoWithThumbnail();
    }

    // 개인 마이페이지 데이터
    @Override
    public Map<String, Object> getMyPageData(HttpSession session, Model model) {
        PersonalMemberDTO member = (PersonalMemberDTO) session.getAttribute("member");
        if(member == null){
            throw new UnauthenticatedException("로그인이 필요한 서비스입니다.");
        }

        Long memberId = member.getId();
        String memberNickname = member.getMemberNickName();

        Map<String, Object> result = new HashMap<>();

        // 1. 내가 쓴 전체 독후감 수
        int totalPosts = memberDAO.findMyBookPostCount(memberId);

        // 2. 이번 달 쓴 독후감 수
        int monthlyPosts = memberDAO.findMyMonthlyBookPostCount(memberId);

        // 3. 이번 달 평균 독후감 수
        int averagePosts = memberDAO.findAverageBookPostCount();

        // 4. 내 마일리지
        int mileage = memberDAO.findMyMileage(memberId);

        // 5. 팔로워 수
        int followers = favoriteDAO.findMyFollowers(memberId);

        // 6. 팔로우 수
        int following = favoriteDAO.findMyFollowing(memberId);

        // 7. 찜한 도서 정보 (isbn만 가져와서 API 조회)
        List<Long> isbnList = favoriteDAO.findMyScrappedBooks(memberId);
        List<AladinBookDTO> scrappedBooks = aladinService.getBooksByIsbnList(isbnList);

        // 결과 담기
        result.put("totalPosts", totalPosts);
        result.put("monthlyPosts", monthlyPosts);
        result.put("averagePosts", averagePosts);
        result.put("mileage", mileage);
        result.put("followers", followers);
        result.put("following", following);
        result.put("scrappedBooks", scrappedBooks);
        result.put("memberNickName", memberNickname);

        // 프사
        FileDTO profileImage = toFileDTO(memberDAO.findMyProfile(memberId));

        if (profileImage == null) {
            profileImage = new FileDTO();
            profileImage.setFilePath("images/common");
            profileImage.setFileName("user_profile_example.png");
        }
        model.addAttribute("file", profileImage);

        return result;
    }


    @Override
    // 비밀번호 유효성검사
    public boolean checkPassword(Long memberId, String password) {
        return memberDAO.checkPassword(memberId, password);
    }

    @Override
    // 세션에서 개인회원정보 가져오기
    public PersonalMemberDTO getCurrentMember(HttpSession session) {
        return (PersonalMemberDTO) session.getAttribute("member");
    }

    @Override
    // 프사 가져오기
    public String getProfileImageUrl(Long memberId) {
        FileVO profileFile = memberDAO.findMyProfile(memberId);

        if (profileFile != null) {
            return "/personal/profile?path=" + profileFile.getFilePath() + "&name=" + profileFile.getFileName();
        } else {
            return "/images/common/user_profile_example.png";
        }
    }

    // 프사 수정
    @Override
    public void updateProfileImage(Long memberId, MultipartFile file) {
        Long fileId = memberDAO.selectProfileFileId(memberId);
        if (fileId == null) {
            throw new IllegalStateException("프로필 이미지가 존재하지 않습니다.");
        }

        String todayPath = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        String rootPath = "C:/upload/" + todayPath;
        File uploadDir = new File(rootPath);
        if (!uploadDir.exists()) uploadDir.mkdirs();

        String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();

        FileDTO dto = new FileDTO();
        dto.setId(fileId);
        dto.setFilePath(todayPath);
        dto.setFileName(fileName);

        try {
            file.transferTo(new File(rootPath, fileName));
            memberDAO.updateMemberFile(dto.toVO());
        } catch (IOException e) {
            throw new RuntimeException("프로필 이미지 업데이트 실패", e);
        }
    }

    // 프사 삭제
    @Override
    public void deleteProfileImage(Long memberId) {
        Long fileId = memberDAO.selectProfileFileId(memberId);
        if (fileId == null) return;

        // 파일 경로 조회
        FileVO fileVO = memberDAO.findMyProfile(memberId);
        String fullPath = "C:/upload/" + fileVO.getFilePath() + "/" + fileVO.getFileName();

        // DB 삭제 (순서: 서브키 → 슈퍼키)
        memberDAO.deleteMemberProfile(memberId);
        memberDAO.deleteMemberFile(fileId);

        // 실제 파일 삭제
        File file = new File(fullPath);
        if (file.exists()) {
            file.delete();
        }
    }

    // 프사 생성
    @Override
    public void saveNewProfileImage(MultipartFile file, Long memberId) {
        if (file == null || file.isEmpty()) {
            throw new IllegalArgumentException("업로드된 파일이 없습니다.");
        }

        try {
            // 1. 경로 준비
            String todayPath = getPath(); // 예: 2025/05/06
            String rootPath = "C:/upload/" + todayPath;
            File uploadDir = new File(rootPath);
            if (!uploadDir.exists()) uploadDir.mkdirs();

            // 2. 파일 이름 설정 및 저장
            String uuidFileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
            file.transferTo(new File(uploadDir, uuidFileName));

            // 3. DTO에 세팅
            FileDTO fileDTO = new FileDTO();
            fileDTO.setFileName(uuidFileName);
            fileDTO.setFilePath(todayPath);

            // 4. DB에 파일 insert → 생성된 id 필요
            FileVO fileVO = fileDTO.toVO();
            memberDAO.insertProfileFile(fileVO);

            // 5. tbl_member_profile용 DTO 설정
            MemberProfileDTO profileDTO = new MemberProfileDTO();
            profileDTO.setId(fileVO.getId()); // insert 후 id가 세팅돼야 함
            profileDTO.setMemberId(memberId);

            // 6. member_profile 테이블에 insert
            memberDAO.insertMemberProfile(profileDTO.toVO());

        } catch (IOException e) {
            throw new RuntimeException("프로필 이미지 저장 실패", e);
        }
    }

    // 파일 생성 또는 수정
    @Override
    public void saveOrUpdateProfileImage(MultipartFile file, Long memberId) {
        Long fileId = memberDAO.selectProfileFileId(memberId);
        if (fileId == null) {
            saveNewProfileImage(file, memberId);
        } else {
            updateProfileImage(memberId, file);
        }
    }

    // 오늘 날짜로 경로 반환
    private String getPath(){
        return LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
    }

    // 개인회원 닉네임 변경
    public void updateNickname(PersonalMemberVO personalMemberVO){
        memberMapper.updateNickname(personalMemberVO);
    };

    // 개인회원 비밀번호 변경
    public void updateMemberPassword(PersonalMemberVO personalMemberVO){
        memberMapper.updateMemberPassword(personalMemberVO);
    };
}
