package com.binghe.dao.before_class;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import com.binghe.AppConfig;
import com.binghe.dao.UserDao;
import com.binghe.domain.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * 기본 SpringExtension 테스트
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = AppConfig.class)
public class UserDaoTest {

    @Autowired
    private UserDao userDao;

    @DisplayName("IoC 컨테이너가 UserDao 의존성을 잘 주입해주는지 테스트")
    @Test
    void dependency() {
        assertThat(userDao).isNotNull();
        System.out.println(userDao);
    }

    @Test
    void addAndGet() {
        userDao.deleteAll();
        assertThat(userDao.count()).isEqualTo(0);

        User user1 = new User(1L, "binghe", "password");
        User user2 = new User(2L, "jiwoo", "password");

        userDao.add(user1);
        userDao.add(user2);
        assertThat(userDao.count()).isEqualTo(2);

        User foundUser1 = userDao.findById(user1.getId());
        User foundUser2 = userDao.findById(user2.getId());

        assertAll(
            () -> assertThat(foundUser1.getId()).isEqualTo(user1.getId()),
            () -> assertThat(foundUser1.getName()).isEqualTo(user1.getName()),
            () -> assertThat(foundUser1.getPassword()).isEqualTo(user1.getPassword()),
            () -> assertThat(foundUser2.getId()).isEqualTo(user2.getId()),
            () -> assertThat(foundUser2.getName()).isEqualTo(user2.getName()),
            () -> assertThat(foundUser2.getPassword()).isEqualTo(user2.getPassword())
        );
    }

    @DisplayName("존재하지 않는 유저면 예외를 던진다.")
    @Test
    void get_negative() {
        userDao.deleteAll();
        assertThat(userDao.count()).isEqualTo(0);

        assertThatThrownBy(() -> userDao.findById(0L))
            .isInstanceOf(EmptyResultDataAccessException.class);
    }

    @Test
    public void deteleAll() {
        User user1 = new User(1L, "홍길동", "1234");
        User user2 = new User(2L, "토비", "4567");

        userDao.deleteAll();
        assertThat(userDao.count()).isEqualTo(0);

        userDao.add(user1);
        userDao.deleteAll();
        assertThat(userDao.count()).isEqualTo(0);
    }

    @Test
    public void getCount() {
        User user1 = new User(1L, "홍길동", "1234");
        User user2 = new User(2L, "토비", "4567");
        User user3 = new User(3L, "자바", "8913");

        // 테스트 1
        userDao.deleteAll();
        assertThat(userDao.count()).isEqualTo(0);

        // 테스트 2
        userDao.add(user1);
        assertThat(userDao.count()).isEqualTo(1);

        // 테스트 3
        userDao.add(user2);
        assertThat(userDao.count()).isEqualTo(2);

        // 테스트 4
        userDao.add(user3);
        assertThat(userDao.count()).isEqualTo(3);
    }
}
