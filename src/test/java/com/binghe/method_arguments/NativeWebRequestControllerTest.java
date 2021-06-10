package com.binghe.method_arguments;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.binghe.WebConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = WebConfig.class)
@WebAppConfiguration
class NativeWebRequestControllerTest {

    @Autowired
    private NativeWebRequestController nativeWebRequestController;
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(nativeWebRequestController).build();
    }

    @Test
    void dependency() {
        assertThat(nativeWebRequestController).isNotNull();
        assertThat(mockMvc).isNotNull();
    }

    @DisplayName("NativeWebReqest - 요청 header의 내용을 그대로 반환하는 테스트")
    @Test
    void extractHeader() throws Exception {
        mockMvc.perform(get("/test/nativewebrequest").header("test", "binghe"))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().string("NativeWebRequest Test - binghe"));
    }
}
