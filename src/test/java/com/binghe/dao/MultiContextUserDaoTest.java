package com.binghe.dao;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * 테스트 주제: JUnit을 돌리는데 SpringExtension을 두 개 켜고, 각각 다른 빈 설정을 할 수 있는가?
 * 결과: 가능함 -> 각각 테스트는 서로 다른 컨텍스트 스코프를 가진다.
 *      만약 여러 개의 테스트 클래스가 있는데 모두 같은 설정파일을 가진다면 스프링은 컨텍스트를 공유하게 해준다.
 *
 * ps. 직접 console에 출력되는 객체 hashCode로 확인해야한다..
 */

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TestConfig.class)
public class MultiContextUserDaoTest {

    @Autowired
    private UserDao userDao;

    @Test
    void dependency() {
        assertThat(userDao).isNotNull();
        System.out.println(userDao);
    }
}
