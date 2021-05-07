package com.binghe.dao.before_class;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

import com.binghe.AppConfig;
import com.binghe.dao.UserDao;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * 클래스의 테스트가 시작하기 전에 context 재생성
 * - 모든 케이스가 context의 상태에 영향을 끼치지 않음
 * - FreshContextTests
 * ps.테스트하기 위해선 before_class 패키지를 실행하면 된다.
 */

@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = AppConfig.class)
class BeforeClassTest {

    // 아래 테스트 케이스들은 새로운 context에서 실행된다.

    @Autowired
    private UserDao userDao;

    @DisplayName("IoC 컨테이너가 UserDao 의존성을 잘 주입해주는지 테스트")
    @Test
    void dependency() {
        assertThat(userDao).isNotNull();
        System.out.println(userDao);
    }
}