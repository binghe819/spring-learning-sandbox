package com.binghe;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.context.WebApplicationContext;

/**
 * @ExtendWith만 사용하면 IoC 컨테이너만 켜진다. (ApplicationContext)
 * 즉, 서블릿 (ex. Dispatcher Servlet) 관련된 빈이 등록되어 있지 않는다.
 * @WebAppConfiguration을 테스트 클래스에 넣어주면 WebApplicationContext가 켜진다.
 * 즉, 서블릿 관련된 빈이 등록된다.
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = AppConfig.class)
@WebAppConfiguration
public class WebApplicationContextDependencyTest {

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private WebApplicationContext webApplicationContext;


    @Test
    void test() {
        assertThat(applicationContext).isNotNull();
        assertThat(webApplicationContext).isNotNull();
    }
}

