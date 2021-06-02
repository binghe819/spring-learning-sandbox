package com.binghe.method_arguments;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.binghe.WebConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
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
class WebRequestControllerTest {

    @Autowired
    private WebRequestController webRequestController;
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(webRequestController).build();
    }

    @Test
    void dependency() {
        assertThat(webRequestController).isNotNull();
        assertThat(mockMvc).isNotNull();
    }

    @DisplayName("WebRequest - 요청 header의 내용을 그대로 반환하는 테스트")
    @Test
    void extractHeader() throws Exception {
        mockMvc.perform(get("/test/webrequest").header("test", "binghe"))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().string("WebRequest Test - binghe"));
    }

    @Disabled
    @DisplayName("WebRequest - 요청 Body의 내용을 그대로 반환하는 테스트")
    @Test
    void extractBody() throws Exception {
        // TODO: POST방식으로 요청 (WebRequest는 Body를 어떤 방식으로 사용하는지 테스트)
        mockMvc.perform(post("/test/webrequest"))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().string("WebRequest Test - binghe"));
    }
}