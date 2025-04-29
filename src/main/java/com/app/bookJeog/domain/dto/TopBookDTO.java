package com.app.bookJeog.domain.dto;


import com.app.bookJeog.domain.vo.TopBookVO;
import com.fasterxml.jackson.annotation.JsonProperty;
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

public class TopBookDTO {
    private TopBookVO topBookVO;
    private String imageUrl;
}
