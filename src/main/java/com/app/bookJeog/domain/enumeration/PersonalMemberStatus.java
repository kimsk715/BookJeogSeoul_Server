package com.app.bookJeog.domain.enumeration;

import com.fasterxml.jackson.annotation.JsonCreator;

// enum은 상수들을 모아놓은(열거한) 집합이며,
// Enum 클래스를 직접 상속받지 않고 컴파일 과정에서 상속받게 된다.
// 각 상수들은 인스턴스로 만들어지며, 각 인스턴스는 모두 해당 enum 타입이다.
// 각 상수는 모두 static으로 처리되고, enum Heap 메모리에 할당된다.
public enum PersonalMemberStatus {
    ACTIVE("활성"),
    DORMANCY("휴면"),
    SUSPENDED("정지"),
    DELETE("정지");
    

    private final String code;

    private PersonalMemberStatus(String code){
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
    public static PersonalMemberStatus forCode(String code){
//        화면에서 전달받은 문자열(code)을
        for(PersonalMemberStatus personalMemberStatus : PersonalMemberStatus.values()){
//            전체 code와 비교하여 같다면
            if(personalMemberStatus.getCode().equals(code)){
//                해당 code의 memberType 객체를 리턴한다.
                return personalMemberStatus;
            }
        }
        throw new IllegalArgumentException("존재하지 않는 값");
    }
}