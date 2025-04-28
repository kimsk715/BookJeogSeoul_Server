package com.app.bookJeog.domain.dto;


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
public class TopBookPostDTO {
    @EqualsAndHashCode.Include
    private Long id;
    private String imageUrl;
    private String title;
    private String author;
    private Long isbn;

}
