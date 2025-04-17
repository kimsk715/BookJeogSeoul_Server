package com.app.bookJeog.domain.enumeration;

import com.fasterxml.jackson.annotation.JsonCreator;

// enum은 상수들을 모아놓은(열거한) 집합이며,
// Enum 클래스를 직접 상속받지 않고 컴파일 과정에서 상속받게 된다.
// 각 상수들은 인스턴스로 만들어지며, 각 인스턴스는 모두 해당 enum 타입이다.
// 각 상수는 모두 static으로 처리되고, enum Heap 메모리에 할당된다.
public enum CommentReportType {
    ABUSE("욕설"),
    SEXUAL("음란성"),
    SPAM("광고성"),
    SPOILER("스포일러"),
    ETC("기타");


    private final String code;

    private CommentReportType(String code){
        this.code = code;
    }

    public String getCode(){
        return code;
    }

//    직렬화: JAVA객체를 문자열(JSON 형식)로 변환하는 작업
//    역직렬화: 문자열(JSON 형식)으로 저장되어 있는 값을 JAVA객체로 변환하는 작업

    //    JsonCreator
//    RESTful API에서 json형식으로 enum을 받아야할 때,
//    직접 작성한 code 혹은 label이 있을 경우 재정의해야한다.
    @JsonCreator
    public static CommentReportType forCode(String code){
//        화면에서 전달받은 문자열(code)을
        for(CommentReportType commentReportType  : CommentReportType.values()){
//            전체 code와 비교하여 같다면
            if(commentReportType.getCode().equals(code)){
//                해당 code의 memberType 객체를 리턴한다.
                return commentReportType;
            }
        }
        throw new IllegalArgumentException("존재하지 않는 값");
    }
}