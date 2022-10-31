package com.example.rbac1.dao.handle;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ListStringConvertHandle<T extends List<String>> extends BaseTypeHandler<T> {

    String separator = "!@@!";

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, T parameter, JdbcType jdbcType) throws SQLException {
        Joiner joiner = Joiner.on(separator);
        String join = joiner.join(parameter);
        ps.setString(i, join);
    }

    @Override
    public T getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String resStr = rs.getString(columnName);
        List<String> resList = Lists.newArrayList(resStr.split(separator));
        return (T) resList;
    }

    @Override
    public T getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String resStr = rs.getString(columnIndex);
        List<String> resList = Lists.newArrayList(resStr.split(separator));
        return (T) resList;
    }

    @Override
    public T getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String resStr = cs.getString(columnIndex);
        List<String> resList = Lists.newArrayList(resStr.split(separator));
        return (T) resList;
    }
}
