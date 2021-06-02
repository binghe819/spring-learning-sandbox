package com.binghe.method_arguments;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.binghe.WebConfig;
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
class PathVariableControllerTest {

    @Autowired
    private PathVariableController pathVariableController;
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(pathVariableController).build();
    }

    @Test
    void dependency() {
        assertThat(pathVariableController).isNotNull();
        assertThat(mockMvc).isNotNull();
    }

    @DisplayName("@PathVariable(성공케이스) - URI 템플릿 변수를 읽을 수 있다.")
    @Test
    void pathVariable() throws Exception {
        mockMvc.perform(get("/test/pathvariable/1"))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("id").value(1));
    }

    @DisplayName("@PathVariable(실패케이스) - URI에 템플릿 변수를 주지 않을 경우 404가 발생한다.")
    @Test
    void pathVariableWithNone() throws Exception {
        mockMvc.perform(get("/test/pathvariable"))
            .andDo(print())
            .andExpect(status().is(404));
    }

    @DisplayName("@PathVariable(실패케이스) - URI에 맞지 않는 데이터 타입을 보낼 경우 400(MethodArgumentTypeMismatchException)가 발생한다.")
    @Test
    void pathVariableWithWrongDataType() throws Exception {
        mockMvc.perform(get("/test/pathvariable/hi"))
            .andDo(print())
            .andExpect(status().is(400));
    }
}