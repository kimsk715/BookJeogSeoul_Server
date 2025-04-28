package com.app.bookJeog.service;

import com.app.bookJeog.domain.dto.*;
import com.app.bookJeog.domain.enumeration.MemberType;
import com.app.bookJeog.domain.vo.MemberVO;
import com.app.bookJeog.domain.vo.PersonalMemberVO;
import com.app.bookJeog.domain.vo.SponsorMemberVO;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Optional;

public interface MemberService {
        public List<PersonalMemberVO> getAllPersonal(Pagination pagination);

        public int countAllPersonal(Pagination pagination);

    // 회원가입
    public default MemberVO toMemberVO  () {
        return MemberVO.builder().memberType(MemberType.PERSONAL).build();
    }

    public default MemberVO toSponsorMember() {
        return MemberVO.builder().memberType(MemberType.SPONSOR).build();
    }

    public default MemberVO toAdmin() {
        return MemberVO.builder().memberType(MemberType.ADMIN).build();
    }
    public default PersonalMemberVO toPersonalMemberVO(MemberPersonalMemberDTO memberPersonalMemberDTO) {
        return PersonalMemberVO.builder()
                .id(memberPersonalMemberDTO.getId())
                .memberBirth(memberPersonalMemberDTO.getMemberBirth())
                .memberPassword(memberPersonalMemberDTO.getMemberPassword())
                .memberName(memberPersonalMemberDTO.getMemberName())
                .memberGender(memberPersonalMemberDTO.getMemberGender())
                .memberPhone(memberPersonalMemberDTO.getMemberPhone())
                .memberEmail(memberPersonalMemberDTO.getMemberEmail())
                .memberNickName(memberPersonalMemberDTO.getMemberNickName())
                .build();
    }


    // 이메일 중복검사
    public Optional<PersonalMemberVO> checkEmail(String email);

    public SponsorMemberVO getSponsorMemberById(Long id);

    // 로그인 ( 화면에서 입력받은 DTO 를 VO로 )
    public default PersonalMemberVO toPersonalMemberVO(PersonalMemberDTO personalMemberDTO) {
        return PersonalMemberVO.builder()
                .id(personalMemberDTO.getId())
                .memberNickName(personalMemberDTO.getMemberNickName())
                .memberMileage(personalMemberDTO.getMemberMileage())
                .memberEmail(personalMemberDTO.getMemberEmail())
                .memberPassword(personalMemberDTO.getMemberPassword())
                .memberName(personalMemberDTO.getMemberName())
                .memberGender(personalMemberDTO.getMemberGender())
                .memberBirth(personalMemberDTO.getMemberBirth())
                .memberPhone(personalMemberDTO.getMemberPhone())
                .build();
    }
    

    public default PersonalMemberDTO toPersonalMemberDTO(PersonalMemberVO personalMemberVO) {
        PersonalMemberDTO personalMemberDTO = new PersonalMemberDTO();
        personalMemberDTO.setId(personalMemberVO.getId());
        personalMemberDTO.setMemberBirth(personalMemberVO.getMemberBirth());
        personalMemberDTO.setMemberEmail(personalMemberVO.getMemberEmail());
        personalMemberDTO.setMemberPassword(personalMemberVO.getMemberPassword());
        personalMemberDTO.setMemberName(personalMemberVO.getMemberName());
        personalMemberDTO.setMemberGender(personalMemberVO.getMemberGender());
        personalMemberDTO.setMemberPhone(personalMemberVO.getMemberBirth());
        return personalMemberDTO;
    }

    public void updateMemberStatus(Long memberId);

    public MemberVO getById(Long id);


    public PersonalMemberVO getPersonalMember(Long memberId);

    public List<SponsorMemberDTO> getAllSponsor(Pagination pagination);

    public default SponsorMemberDTO toSponsorMemberDTO(SponsorMemberVO sponsorMemberVO) {
        SponsorMemberDTO sponsorMemberDTO = new SponsorMemberDTO();
        sponsorMemberDTO.setId(sponsorMemberVO.getId());
        sponsorMemberDTO.setSponsorId(sponsorMemberVO.getSponsorId());
        sponsorMemberDTO.setSponsorEmail(sponsorMemberVO.getSponsorEmail());
        sponsorMemberDTO.setSponsorName(sponsorMemberVO.getSponsorName());
        sponsorMemberDTO.setSponsorPassword(sponsorMemberVO.getSponsorPassword());
        sponsorMemberDTO.setSponsorMemberStatus(sponsorMemberVO.getSponsorMemberStatus());
        sponsorMemberDTO.setSponsorPhoneNumber(sponsorMemberVO.getSponsorPhoneNumber());
        sponsorMemberDTO.setSponsorMainAddress(sponsorMemberVO.getSponsorMainAddress());
        sponsorMemberDTO.setSponsorSubAddress(sponsorMemberVO.getSponsorSubAddress());
        sponsorMemberDTO.setCreatedDate(sponsorMemberVO.getCreatedDate());
        sponsorMemberDTO.setUpdatedDate(sponsorMemberVO.getUpdatedDate());

        return sponsorMemberDTO;

    }



    public int countAllSponsor(Pagination pagination);

    public void insertSponsorMember(SponsorMemberDTO sponsorMemberDTO);

    public List<AdminDTO> getAllAdmin(Pagination pagination);

    public int countAllAdmin(Pagination pagination);

    public void insertAdmin(AdminDTO adminDTO);


    public default String getKakaoAccessToken(String code){
        String accessToken = null;
        String requestURI = "https://kauth.kakao.com/oauth/token";

        try {
            URL url = new URL(requestURI);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            StringBuilder stringBuilder = new StringBuilder();
            BufferedWriter bufferedWriter = null;

            connection.setRequestMethod("POST");
            connection.setDoOutput(true);

            stringBuilder.append("grant_type=authorization_code");
            stringBuilder.append("&client_id=c87c26c641832d92e09c529afe085195");
            stringBuilder.append("&redirect_uri=http://localhost:10000/kakao/login");
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

}

