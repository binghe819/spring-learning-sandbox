package com.binghe.dao.after_each_test_method;

import static org.assertj.core.api.Assertions.assertThat;

import com.binghe.AppConfig;
import com.binghe.dao.UserDao;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * 클래스의 모든 테스트 케이스마다 시작하기 이후에 context 재생성
 * - 모든 케이스가 context의 상태에 영향을 끼침
 * - ContextDirtyingTests
 */

@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = AppConfig.class)
public class AfterEachMethodTest {

    // 모든 케이스가 context의 상태에 영향을 끼침

    private static UserDao temp;

    @Autowired
    private UserDao userDao;

    @DisplayName("매 메서드 실행후 Context를 새로 만든다")
    @RepeatedTest(5)
    void test1() {
        System.out.println(userDao);
        assertThat(userDao).isNotSameAs(temp);
        temp = userDao;
    }
}
