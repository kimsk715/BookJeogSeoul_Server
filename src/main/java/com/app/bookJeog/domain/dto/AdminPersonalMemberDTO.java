package com.app.bookJeog.domain.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@ToString
@Getter
@Setter
@NoArgsConstructor

public class AdminPersonalMemberDTO {
    private List<PersonalMemberDTO> personalMemberDTOList;
    private Pagination pagination;
}
