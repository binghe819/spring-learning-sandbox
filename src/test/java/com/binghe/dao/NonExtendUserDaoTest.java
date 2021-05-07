package com.binghe.dao;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 테스트 주제: SpringExtension 애노테이션이 없으면 IoC 컨테이너를 사용하지 못하는가?
 * 결과: 예! (당연!)
 * 나의 생각: JUnit이 켜지면서 SpringExtension이 확장되어 있다면 전역적인 IoC 컨테이너를 만든다.
 */
public class NonExtendUserDaoTest {

    @Autowired
    private UserDao userDao;

    @Test
    void dependency() {
        assertThat(userDao).isNull();
    }
}
