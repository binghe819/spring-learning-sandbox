package com.binghe.dao.after_class;

import static org.assertj.core.api.Assertions.assertThat;

import com.binghe.AppConfig;
import com.binghe.dao.UserDao;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * Default 설정
 *
 * 클래스의 테스트가 모두 끝난 다음 context 재생성
 * - 테스트 케이스가 context의 @Bean의 상태에 영향을 끼침
 * - ContextDirtyingTests
 */

@DirtiesContext
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = AppConfig.class)
public class AfterClassTest {

    // 테스트 케이스가 context의 @Bean의 상태에 영향을 끼침

    private static UserDao temp;

    @Autowired
    private UserDao userDao;

    @DisplayName("매 메서드 실행후 Context를 새로 만든다")
    @RepeatedTest(5)
    void test1() {
        System.out.println(userDao);
        userDao = temp;
        assertThat(userDao).isSameAs(temp);
    }
}
