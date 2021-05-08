package com.binghe.dao;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import com.binghe.AppConfig;
import com.binghe.domain.User;
import java.sql.PreparedStatement;
import javax.sql.DataSource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * @Sql
 *
 */

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = AppConfig.class)
@Sql(scripts = "classpath:schema.sql")
class UserDaoTest {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    private DataSource dataSource;

    @BeforeEach
    void setUp() {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Test
    void dependency() {
        assertThat(jdbcTemplate).isNotNull();
    }

    @Test
    void insert() {
        // given
        User user = new User(1L, "binghe", "hi");

        // when
        Long id = add(user);
        User foundUser = findById(id);

        // then
        assertAll(
            () -> assertThat(user.getId()).isEqualTo(foundUser.getId()),
            () -> assertThat(user.getName()).isEqualTo(foundUser.getName()),
            () -> assertThat(user.getPassword()).isEqualTo(foundUser.getPassword())
        );
    }

    private Long add(User user) {
        String sql = "INSERT INTO tests(id, name, password) VALUES (?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update((con) -> {
            PreparedStatement ps = con.prepareStatement(sql, new String[]{"id"});
            ps.setLong(1, user.getId());
            ps.setString(2, user.getName());
            ps.setString(3, user.getPassword());
            return ps;
        }, keyHolder);
        return keyHolder.getKey().longValue();
    }

    private User findById(Long id) {
        String sql = "SELECT * FROM tests WHERE id =?";
        return jdbcTemplate.queryForObject(
            sql,
            (rs, rowNum) -> {
                Long id_ = rs.getLong("id");
                String name = rs.getString("name");
                String password = rs.getString("password");
                return new User(id_, name, password);
            },
            id);
    }

    private void deleteAll() {
        String sql = "DELETE FROM tests";
        jdbcTemplate.update(sql);
    }

    private int count() {
        String sql = "SELECT COUNT(*) FROM tests";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }
}
