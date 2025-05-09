package com.app.bookJeog.domain.dto;


import com.app.bookJeog.domain.enumeration.AlarmStatus;
import com.app.bookJeog.domain.vo.AlarmVO;
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
public class TopViewBookDTO {
    @EqualsAndHashCode.Include
    private Long id;
    private String imageUrl;
    private String title;
    private String author;
    private String description;
    private Long isbn;

}
