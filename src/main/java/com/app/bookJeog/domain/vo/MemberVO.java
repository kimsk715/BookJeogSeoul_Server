package com.app.bookJeog.domain.vo;

import com.app.bookJeog.domain.enumeration.MemberType;
import lombok.Getter;


@Getter
public class MemberVO extends Period {
    private Long id;
    private MemberType memberType;
}
