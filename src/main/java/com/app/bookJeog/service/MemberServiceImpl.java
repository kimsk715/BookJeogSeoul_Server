package com.app.bookJeog.service;


import com.app.bookJeog.domain.dto.Pagination;
import com.app.bookJeog.domain.vo.MemberVO;
import com.app.bookJeog.domain.vo.PersonalMemberVO;
import com.app.bookJeog.domain.dto.MemberPersonalMemberDTO;
import com.app.bookJeog.domain.dto.PersonalMemberDTO;
import com.app.bookJeog.mapper.MemberMapper;
import com.app.bookJeog.repository.MemberDAO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;
import java.util.List;

@Service
@RequiredArgsConstructor @Transactional(rollbackFor = Exception.class)
@Slf4j
public class MemberServiceImpl implements MemberService {
    private final MemberDAO memberDAO;
    private final MemberMapper memberMapper;
    private final PersonalMemberDTO personalMemberDTO;



    @Override
    public List<PersonalMemberVO> getAllPersonal(Pagination pagination) {
        return memberDAO.findAllPersonal(pagination);
    }

    @Override
    public int countAllPersonal(Pagination pagination) {
        return memberDAO.countAllPersonal(pagination);
    }
    // 이메일 중복검사
    public Optional<PersonalMemberVO> checkEmail(String email) {
        return memberMapper.selectByEmail(email);
    }


    // 닉네임 중복검사 만들어야함
    //일반회원 회원가입
    public void insertPersonalMember(MemberPersonalMemberDTO memberPersonalMemberDTO) {
        MemberVO memberVO = toMemberVO();
        memberMapper.insertCommonMember(memberVO);

        memberPersonalMemberDTO.setId(memberVO.getId());
        PersonalMemberVO personalMemberVO = toPersonalMemberVO(memberPersonalMemberDTO);
        personalMemberVO = toPersonalMemberVO(memberPersonalMemberDTO);

        memberMapper.insertPersonalMember(personalMemberVO);
    }


    // 로그인
    public void loginPersonalMember(MemberPersonalMemberDTO memberPersonalMemberDTO) {
        memberMapper.
    }
}
