package com.app.bookJeog.mybatis;


import com.app.bookJeog.domain.enumeration.AdminMemberStatus;
import com.app.bookJeog.domain.enumeration.BookReceivedStatus;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@MappedTypes(AdminMemberStatus.class)
public class AdminMemberStatusHandler implements TypeHandler<AdminMemberStatus> {
    @Override
    public void setParameter(PreparedStatement ps, int i, AdminMemberStatus parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, parameter.getCode());
    }

    @Override
    public AdminMemberStatus getResult(ResultSet rs, String columnName) throws SQLException {
        switch (rs.getString(columnName)){
            case "재직" :
                return AdminMemberStatus.ACTIVE;
            case "퇴직" :
                return AdminMemberStatus.DELETE;
            case "휴직":
                return AdminMemberStatus.DORMANCY;

        }
        return null;
    }

    @Override
    public AdminMemberStatus getResult(ResultSet rs, int columnIndex) throws SQLException {
        switch (rs.getString(columnIndex)){
            case "재직" :
                return AdminMemberStatus.ACTIVE;
            case "퇴직" :
                return AdminMemberStatus.DELETE;
            case "휴직":
                return AdminMemberStatus.DORMANCY;
        }
        return null;
    }

    @Override
    public AdminMemberStatus getResult(CallableStatement cs, int columnIndex) throws SQLException {
        switch (cs.getString(columnIndex)){
            case "재직" :
                return AdminMemberStatus.ACTIVE;
            case "퇴직" :
                return AdminMemberStatus.DELETE;
            case "휴직":
                return AdminMemberStatus.DORMANCY;
        }
        return null;
    }
}
