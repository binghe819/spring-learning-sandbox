package com.binghe.dao;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

import com.binghe.AppConfig;
import com.binghe.domain.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * 여러 테스트 클래스와 메서드에서 IoC 컨테이너를 공유하기 위해서 Spring에서는 JUnit Extension을 제공해준다.
 * - ExtendWith: JUnit의 테스트 확장
 *   - SpringExtension: SpringExtension integrates the Spring TestContext Framework into JUnit 5's Jupiter programming model.
 * - ContextConfiguration: defines class-level metadata that is used to determine
 *  how to load and configure an ApplicationContext for integration tests.
 * 위 애노테이션이 붙은 테스트에서는 모두 IoC 컨테이너를 공유하게 된다. (통합 테스트)
 * 즉, 여러 개의 테스트 클래스가 있는데 모두 같은 설정파일을 가진다면 스프링은 컨텍스트를 공유하게 해준다.
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = AppConfig.class)
class UserDaoTest {

    @Autowired
    private UserDao userDao;

    @DisplayName("IoC 컨테이너가 UserDao 의존성을 잘 주입해주는지 테스트")
    @Test
    void dependency() {
        assertThat(userDao).isNotNull();
        System.out.println(userDao);
    }

    @DisplayName("IoC 컨테이너는 싱글톤으로 빈을 주입해준다.")
    @RepeatedTest(value = 5)
    void singleton() {
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