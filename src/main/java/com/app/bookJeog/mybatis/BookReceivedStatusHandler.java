package com.app.bookJeog.mybatis;


import com.app.bookJeog.domain.enumeration.BookReceivedStatus;
import com.app.bookJeog.domain.enumeration.MemberType;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@MappedTypes(BookReceivedStatus.class)
public class BookReceivedStatusHandler implements TypeHandler<BookReceivedStatus> {
    @Override
    public void setParameter(PreparedStatement ps, int i, BookReceivedStatus parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, parameter.getCode());
    }

    @Override
    public BookReceivedStatus getResult(ResultSet rs, String columnName) throws SQLException {
        switch (rs.getString(columnName)){
            case "수취대기" :
                return BookReceivedStatus.WAITING;
            case "수취완료" :
                return BookReceivedStatus.DONE;

        }
        return null;
    }

    @Override
    public BookReceivedStatus getResult(ResultSet rs, int columnIndex) throws SQLException {
        switch (rs.getString(columnIndex)){
            case "수취대기" :
                return BookReceivedStatus.WAITING;
            case "수취완료" :
                return BookReceivedStatus.DONE;
        }
        return null;
    }

    @Override
    public BookReceivedStatus getResult(CallableStatement cs, int columnIndex) throws SQLException {
        switch (cs.getString(columnIndex)){
            case "수취대기" :
                return BookReceivedStatus.WAITING;
            case "수취완료" :
                return BookReceivedStatus.DONE;
        }
        return null;
    }
}
