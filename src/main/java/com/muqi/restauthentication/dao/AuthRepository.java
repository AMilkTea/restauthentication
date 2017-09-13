package com.muqi.restauthentication.dao;

import com.muqi.restauthentication.dao.domain.User;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by lyh on 17-4-1.
 */
@Data
@Component
public class AuthRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public AuthRepository(JdbcTemplate jt){ jdbcTemplate = jt; }

    public User queryUserCount(String name, String password) {
        String sql = "select * from users where username = ? and password = ?";
        return jdbcTemplate.queryForObject(sql,new UserRowMapper(), name, password);
    }

    private static final class UserRowMapper implements RowMapper<User> {
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            long id = rs.getLong("id");
            String name = rs.getString("username");
            String password = rs.getString("password");
            String email = rs.getString("email");

            return new User(id, name, password, email);
        }
    }
}
