package com.app.bookJeog.domain.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@NoArgsConstructor
public class Period {
    protected String createdDate;
    protected String updatedDate;

    public Period(String createdDate, String updatedDate) {
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }
}
