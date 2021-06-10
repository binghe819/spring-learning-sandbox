package com.binghe.method_arguments;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.binghe.WebConfig;
import com.binghe.domain.Event;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = WebConfig.class)
@WebAppConfiguration
class RequestBodyControllerTest {

    @Autowired
    private RequestBodyController requestBodyController;
    @Autowired
    private ObjectMapper objectMapper;
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(requestBodyController).build();
    }

    @Test
    void dependency() {
        assertThat(requestBodyController).isNotNull();
        assertThat(objectMapper).isNotNull();
        assertThat(mockMvc).isNotNull();
    }

    @DisplayName("@RequestBody - 요청 본문(body)에 들어있는 데이터를 HttpMessageConverter를 통해 변환한 객체로 받아올 수 있다.")
    @Test
    void requestBody() throws Exception {
        Event event = new Event(1, "binghe");
        String json = objectMapper.writeValueAsString(event);
        mockMvc.perform(post("/test/requestbody")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("id").value("1"))
            .andExpect(jsonPath("name").value("binghe"));
    }

    @DisplayName("@RequestBody - 요청 본문(body)에 데이터가 존재하지 않을 경우 null (기본 타입의 경우 초깃값으로 설정된다. (Validation을 사용하지 않을 경우")
    @Test
    void requestBodyWithNone() throws Exception {
        Event event = new Event(null, null);
        String json = objectMapper.writeValueAsString(event);
        mockMvc.perform(post("/test/requestbody")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("id").isEmpty())
            .andExpect(jsonPath("name").isEmpty());
    }
}