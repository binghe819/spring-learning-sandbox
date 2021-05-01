package com.binghe;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration.Dynamic;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

/**
 * web.xml을 사용하여 DispatcherServlet을 등록하는 방법 대신해서
 * 아래와 같이 자바 코드로 DispatcherServlet을 등록할 수 있다.
 */
public class WebApplication implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        // DispatcherServlet에 등록할 Application Context (IoC 컨테이너) 생성
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.register(WebConfig.class);
        context.refresh();

        // DispatcherServlet 생성 및 ApplicationContext 주입.
        DispatcherServlet dispatcherServlet = new DispatcherServlet(context);
        // ServletContext에 DispatcherServlet 등록
        Dynamic app = servletContext.addServlet("app", dispatcherServlet);
        // app 이하의 모든 요청을 DispatcherServlet이 처리.
        app.addMapping("/app/*");
    }
}
