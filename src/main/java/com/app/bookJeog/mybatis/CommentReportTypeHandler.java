package com.app.bookJeog.mybatis;


import com.app.bookJeog.domain.enumeration.CommentReportType;
import com.app.bookJeog.domain.enumeration.MemberType;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@MappedTypes(CommentReportType.class)
public class CommentReportTypeHandler implements TypeHandler<CommentReportType> {


    // 자바 enum을 db에 저장할 때 문자열로 변환해서 넣어줌
    // MemberType.PERSONAL -> "개인"
    @Override
    public void setParameter(PreparedStatement ps, int i, CommentReportType parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, parameter.getCode());
    }

    
    // db에서 값을 읽어올 때 해당 컬럼명을 기준으로 문자열 값을 가져와서 
    // 그에 맞는 membertype enum으로 변환해줌
    @Override
    public CommentReportType getResult(ResultSet rs, String columnName) throws SQLException {
        switch (rs.getString(columnName)){
            case "욕설" :
                return CommentReportType.ABUSE;
            case "음란성" :
                return CommentReportType.SEXUAL;
            case "광고성":
                return CommentReportType.SPAM;
            case "스포일러":
                return CommentReportType.SPOILER;
            case "기타":
                return CommentReportType.ETC;
        }
        return null;
    }

    @Override
    public CommentReportType getResult(ResultSet rs, int columnIndex) throws SQLException {
        switch (rs.getString(columnIndex)){
            case "욕설" :
                return CommentReportType.ABUSE;
            case "음란성" :
                return CommentReportType.SEXUAL;
            case "광고성":
                return CommentReportType.SPAM;
            case "스포일러":
                return CommentReportType.SPOILER;
            case "기타":
                return CommentReportType.ETC;
        }
        return null;
    }

    @Override
    public CommentReportType getResult(CallableStatement cs, int columnIndex) throws SQLException {
        switch (cs.getString(columnIndex)){
            case "욕설" :
                return CommentReportType.ABUSE;
            case "음란성" :
                return CommentReportType.SEXUAL;
            case "광고성":
                return CommentReportType.SPAM;
            case "스포일러":
                return CommentReportType.SPOILER;
            case "기타":
                return CommentReportType.ETC;
        }
        return null;
    }
}
