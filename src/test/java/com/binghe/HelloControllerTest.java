package com.binghe;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = WebConfig.class)
class HelloControllerTest {

    @Autowired
    private HelloController helloController;
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(helloController).build();
    }

    @Test
    void dependency() {
        assertThat(helloController).isNotNull();
        assertThat(mockMvc).isNotNull();
    }

    @DisplayName("RequestMapping(성공케이스) - RequestMapping을 사용하고 아무런 HTTP 메서드를 정의하지 않으면 모든 메서드를 처리할 수 있다. - GET")
    @Test
    void helloTest_get() throws Exception {
        mockMvc.perform(get("/hello"))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().string("Hello"));
    }

    @DisplayName("RequestMapping(성공케이스) - RequestMapping을 사용하고 아무런 HTTP 메서드를 정의하지 않으면 모든 메서드를 처리할 수 있다. - POST")
    @Test
    void helloTest_post() throws Exception {
        mockMvc.perform(post("/hello"))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().string("Hello"));
    }

    @DisplayName("RequestMapping(실패케이스) - 맵핑할 수 있는 URI이 없을 경우 404 (NotFound)를 던진다.")
    @Test
    void test() throws Exception {
        mockMvc.perform(get("/none"))
            .andDo(print())
            .andExpect(status().is(404));
    }

    @DisplayName("RequestMapping(실패케이스) - 맵핑하려는 핸들러에 요청에 대한 메서드가 지원하지 않으면 405 (MethodNotAllowed)를 던진다.")
    @Test
    void test_405() throws Exception {
        mockMvc.perform(delete("/test"))
            .andDo(print())
            .andExpect(status().is(405));
    }
}