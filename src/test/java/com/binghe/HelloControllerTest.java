package com.binghe;

import static org.assertj.core.api.Assertions.assertThat;
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

    @DisplayName("RequestMapping - RequestMapping을 사용하고 아무런 HTTP 메서드를 정의하지 않으면 모든 메서드를 처리할 수 있다. - GET")
    @Test
    void helloTest_get() throws Exception {
        mockMvc.perform(get("/hello"))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().string("Hello"));
    }

    @DisplayName("RequestMapping - RequestMapping을 사용하고 아무런 HTTP 메서드를 정의하지 않으면 모든 메서드를 처리할 수 있다. - POST")
    @Test
    void helloTest_post() throws Exception {
        mockMvc.perform(post("/hello"))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().string("Hello"));
    }
}