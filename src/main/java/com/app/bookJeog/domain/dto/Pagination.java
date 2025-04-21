package com.app.bookJeog.domain.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

@Component
@ToString
@Getter
@Setter
@NoArgsConstructor
public class Pagination {
    private int page;
    private int rowCount;
    private int pageCount;
    private int startPage;
    private int endPage;
    private int realEnd;
    private boolean prev, next;
    private int total;
    private int offset;

    public void create(int total) {
        this.page = this.page == 0 ? 1 : this.page;
        this.rowCount = 10;
        this.total = total;
        this.pageCount = 10;
        this.offset = (page - 1) * rowCount;
        this.endPage = (int)(Math.ceil(page / (double)pageCount)) * pageCount;
        this.startPage = endPage - pageCount + 1;
        this.realEnd = (int)(Math.ceil(total / (double)rowCount));
        if(realEnd < endPage) {
            endPage = realEnd == 0 ? 1 : realEnd;
        }
        this.prev = startPage > 1;
        this.next = endPage < realEnd;
    }
}
