package com.binghe.dao.after_method;

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
 * 특정 테스트 메서드를 시작한 이후 context 재생성
 * - 해당 테스트 메서드가 context의 상태에 영향을 끼침
 * - ContextDirtyingTests
 */

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = AppConfig.class)
public class AfterMethodTest {

    private static UserDao temp;

    @Autowired
    private UserDao userDao;

    @DirtiesContext(methodMode = MethodMode.AFTER_METHOD)
    @DisplayName("매 메서드 실행후 Context를 새로 만든다")
    @RepeatedTest(5)
    void test() {
        // 기존 context의 상태를 변경한다.
        System.out.println(userDao);
        assertThat(userDao).isNotSameAs(temp);
        temp = userDao;
    }
}
