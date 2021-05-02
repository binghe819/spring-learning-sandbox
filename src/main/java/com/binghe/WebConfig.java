package com.binghe;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 스프링MVC에선 기본적인 설정을 쉽게 할 수 있도록 아래와 같이 애노테이션과 인터페이스를 제공한다. (이게 아니면 @Bean을 통해 설정해줘야한다)
 * - @EnableWebMvc: MVC의 사용되는 빈들의 기본 설정을 변경하고, DelegatingWebMvcConfiguration을 통해 스프링 MVC(Web관련) 빈들을 쉽게 설정할 수 있게 도와준다.
 * - WebMvcConfigurer: Spring MVC를 자바 기반으로 설정할 때 사용하는 인터페이스
 *   - @EnableWebMvc가 제공하는 빈을 커스터마이징할 수 있는 기능을 제공하는 인터페이스
 */
@Configuration
@ComponentScan
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 인터셉터를 추가하고 싶은 경우 아래 추가해주면 된다.
        WebMvcConfigurer.super.addInterceptors(registry);
    }

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        // ViewResolver관련된 설정은 아래 추가해주면 된다.
        registry.jsp("/WEB-INF/", ".jsp");
    }
}
