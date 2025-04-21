package com.app.bookJeog.domain.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
// 서울도서관 책 정보 VO 여기는 절대 건들지 말아야 함.
@Data
public class BookInfoVO {

    @JsonProperty("TITLE")
    private String title;

    @JsonProperty("BIB_TYPE_NAME")
    private String bibTypeName;

    @JsonProperty("AUTHOR")
    private String author;

    @JsonProperty("CTRLNO")
    private String controlNo;

    @JsonProperty("PUBLER")
    private String publisher;

    @JsonProperty("NBOOK_YN")
    private String newBookYn;

    @JsonProperty("CRITYN")
    private String reviewYn;

    @JsonProperty("VODYN")
    private String vodYn;

    @JsonProperty("OLDYN")
    private String oldBookYn;

    @JsonProperty("ONLNYN")
    private String onlineYn;

    @JsonProperty("ABSYN")
    private String abstractYn;

    @JsonProperty("STRUCT_YN")
    private String contentsYn;

    @JsonProperty("OLD_INTRCN_YN")
    private String introductionYn;

    @JsonProperty("URLYN")
    private String urlYn;

    @JsonProperty("CALL_NO")
    private String callNo;

    @JsonProperty("CLASS_NO")
    private String classNo;

    @JsonProperty("PUBLER_YEAR")
    private String publishedYear;

    @JsonProperty("AUTHOR_NO")
    private String authorNo;

    @JsonProperty("LANG")
    private String languageCode;

    @JsonProperty("LANG_NAME")
    private String languageName;

    @JsonProperty("CONTRY")
    private String countryCode;

    @JsonProperty("CONTRY_NAME")
    private String countryName;

    @JsonProperty("EDITON")
    private String edition;

    @JsonProperty("PAGE")
    private String page;

    @JsonProperty("ISBN")
    private String isbn;

    @JsonProperty("APPEND_INFO")
    private String appendInfo;

    @JsonProperty("LOCA")
    private String locationCode;

    @JsonProperty("LOCA_NAME")
    private String locationName;

    @JsonProperty("CREATE_DATE")
    private String createDate;

    @JsonProperty("SUB_LOCA")
    private String subLocationCode;

    @JsonProperty("SUB_LOCA_NAME")
    private String subLocationName;

    @JsonProperty("LOAN_STATUS")
    private String loanStatus;

    @JsonProperty("LOAN_STATUS_NAME")
    private String loanStatusName;

    @JsonProperty("BIB_TYPE")
    private String bibType;
}
