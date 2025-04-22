package com.app.bookJeog.mybatis;


import com.app.bookJeog.domain.enumeration.MemberType;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@MappedTypes(MemberType.class)
public class MemberTypeHandler implements TypeHandler<MemberType> {


    // 자바 enum을 db에 저장할 때 문자열로 변환해서 넣어줌
    // MemberType.PERSONAL -> "개인"
    @Override
    public void setParameter(PreparedStatement ps, int i, MemberType parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, parameter.getCode());
    }

    
    // db에서 값을 읽어올 때 해당 컬럼명을 기준으로 문자열 값을 가져와서 
    // 그에 맞는 membertype enum으로 변환해줌
    @Override
    public MemberType getResult(ResultSet rs, String columnName) throws SQLException {
        switch (rs.getString(columnName)){
            case "단체" :
                return MemberType.SPONSOR;
            case "개인" :
                return MemberType.PERSONAL;
            case "관리자":
                return MemberType.ADMIN;
        }
        return null;
    }

    @Override
    public MemberType getResult(ResultSet rs, int columnIndex) throws SQLException {
        switch (rs.getString(columnIndex)){
            case "개인":
                return MemberType.PERSONAL;
            case "관리자":
                return MemberType.ADMIN;
            case "단체" :
                return MemberType.SPONSOR;
        }
        return null;
    }

    @Override
    public MemberType getResult(CallableStatement cs, int columnIndex) throws SQLException {
        switch (cs.getString(columnIndex)){
            case "개인":
                return MemberType.PERSONAL;
            case "관리자":
                return MemberType.ADMIN;
            case "단체" :
                return MemberType.SPONSOR;
        }
        return null;
    }
}
