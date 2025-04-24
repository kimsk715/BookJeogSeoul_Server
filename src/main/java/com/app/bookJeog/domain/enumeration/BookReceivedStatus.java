package com.app.bookJeog.domain.enumeration;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;


public enum BookReceivedStatus {
    WAITING("수취대기"),
    DONE("수취완료");


    private final String code;

    private BookReceivedStatus(String code){
        this.code = code;
    }

    @JsonValue
    public String getCode(){
        return code;
    }

    @JsonCreator
    public static BookReceivedStatus forCode(String code){
        for(BookReceivedStatus bookReceivedStatus  : BookReceivedStatus.values()){
            if(bookReceivedStatus.getCode().equals(code)){
                return bookReceivedStatus;
            }
        }
        throw new IllegalArgumentException("존재하지 않는 값");
    }
}