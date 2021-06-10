package com.binghe.method_arguments;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.binghe.WebConfig;
import org.junit.jupiter.api.AfterEach;
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
class RequestParamControllerTest {

    @Autowired
    private RequestParamController requestParamController;
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(requestParamController).build();
    }

    @Test
    void dependency() {
        assertThat(requestParamController).isNotNull();
        assertThat(mockMvc).isNotNull();
    }

    @DisplayName("@RequestParam(성공케이스) - 요청 매개변수에 들어있는 단순 타입 데이터를 메서드 아규먼트로 받아올 수 있다.")
    @Test
    void requestParam() throws Exception {
        mockMvc.perform(get("/test/requestparam?id=1&name=binghe"))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("id").value("1"))
            .andExpect(jsonPath("name").value("binghe"));
    }

    @DisplayName("@RequestParam(실패케이스) - 요청 매개변수를 하나라도 주지 않을 경우 400(MissingServletRequestParameterException)가 발생한다.")
    @Test
    void requestParamWithNone() throws Exception {
        mockMvc.perform(get("/test/requestparam?id=1"))
            .andDo(print())
            .andExpect(status().is(400));
    }

    @DisplayName("@RequestParam(실패케이스) - 요청 매개변수의 타입이 다를 경우 400(MethodArgumentTypeMismatchException)가 발생한다.")
    @Test
    void requestParamWithWrongDataType() throws Exception {
        mockMvc.perform(get("/test/requestparam?id=binghe"))
            .andDo(print())
            .andExpect(status().is(400));
    }

    @DisplayName("@RequestParam(기본값 설정) - 요청 매개변수의 defaultValue설정을 통해 기본값을 설정해줄 수 있다.")
    @Test
    void requestParamDefaultValue() throws Exception {
        mockMvc.perform(get("/test/requestparam/default"))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("id").value("0"));
    }

    @DisplayName("@RequestParam(Map사용) - 요청 매개변수를 Map 형태로 받아올 수 있다. (빈 값일 경우 따로 예외처리를 해줘야하는 단점이 있다.)")
    @Test
    void requestParamWithMap() throws Exception {
        mockMvc.perform(get("/test/requestparam/map?id=1&name=binghe"))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("id").value(1))
            .andExpect(jsonPath("name").value("binghe"));
    }

    @DisplayName("Binding - MessageConverter를 설정해주면 자동 Biding해준다. 대신 @RequestParam애노테이션을 제거해야한다. (ModelAttribute를 사용하는듯")
    @Test
    void requestParamWithBinding() throws Exception {
        mockMvc.perform(get("/test/requestparam/binding")
                .param("id", "1")
                .param("name", "binghe"))
            .andDo(print())
            .andExpect(status().isOk());
    }
}