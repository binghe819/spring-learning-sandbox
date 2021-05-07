package com.binghe.dao.before_method;

import static org.assertj.core.api.Assertions.assertThat;

import com.binghe.AppConfig;
import com.binghe.dao.UserDao;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.MethodMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * 특정 테스트 메서드 시작하기 전에 context 재생성
 * - 모든 케이스가 context의 상태에 영향을 끼치지 않음
 * - FreshContextTests
 */

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = AppConfig.class)
public class BeforeMethodTest {

    private static UserDao temp;

    @Autowired
    private UserDao userDao;

    @DirtiesContext(methodMode = MethodMode.BEFORE_METHOD)
    @DisplayName("매 메서드 실행전 Context를 새로 만든다")
    @RepeatedTest(5)
    void test() {
        // 새로운 context가 필요한 로직에서 사용된다.
        System.out.println(userDao);
        assertThat(userDao).isNotSameAs(temp);
        temp = userDao;
    }
}
