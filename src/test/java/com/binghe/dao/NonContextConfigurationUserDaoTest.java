package com.binghe.dao;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * 테스트 주제: SpringExtension 애노테이션은 있지만 ContextConfiguration 애노테이션이 없다면?
 * 결과: java.lang.IllegalStateException: Failed to load ApplicationContext 예외가 발생한다.
 *     즉, SpringExtension을 사용하기 위해선 IoC컨테이너의 ContextConfiguration이 꼭 필요하다!
 *     아마 스프링 부트는 자동적으로 설정해주지 않을까 싶다.
 *
 * ps. 테스트하기 위해선 아래 ExtendWith 주석을 풀고 실행해보자
 */
//@ExtendWith(SpringExtension.class)
public class NonContextConfigurationUserDaoTest {

    @Autowired
    private UserDao userDao;

    @Test
    void dependency() {
        assertThat(userDao).isNull();
    }
}
