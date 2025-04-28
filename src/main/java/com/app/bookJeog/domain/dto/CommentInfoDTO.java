package com.app.bookJeog.domain.dto;


import com.app.bookJeog.domain.enumeration.CommentStatus;
import com.app.bookJeog.domain.vo.CommentVO;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class CommentInfoDTO {
    private CommentDTO commentDTO;
    private String memberName;



}
