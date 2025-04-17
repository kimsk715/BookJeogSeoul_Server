package com.app.bookJeog.controller;

import com.app.bookJeog.domain.dto.MemberDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestBody;

// 분리하는 이유
// 문서 작업과 비지니스 로직 작업을 분리하여 가독성을 높이고 유지보수 효과를 극대화하기 위함
@Tag(name = "Member", description = "Member API")
public interface MemberControllerDocs {
//
//    @Operation(summary = "회원 가입",
//            description = "회원가입할 때 사용하는 API",
//            parameters = {
//                    @Parameter(name="memberEmail", description = "회원 이메일"),
//                    @Parameter(name="memberPassword", description = "회원 비밀번호"),
//                    @Parameter(name="memberName", description = "회원 이름"),
//            })
//    public void join(@RequestBody MemberDTO memberDTO);
}
