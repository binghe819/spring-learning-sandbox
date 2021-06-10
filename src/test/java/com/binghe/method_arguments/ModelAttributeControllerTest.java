package com.binghe.method_arguments;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = WebConfig.class)
@WebAppConfiguration
class ModelAttributeControllerTest {

    @Autowired
    private ModelAttributeController modelAttributeController;
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(modelAttributeController).build();
    }

    @Test
    void dependency() {
        assertThat(modelAttributeController).isNotNull();
        assertThat(mockMvc).isNotNull();
    }

    @DisplayName("@ModelAttribute - 요청 매개변수를 복합 타입으로 받을 수 있다.")
    @Test
    void modelAttribute() throws Exception {
        mockMvc.perform(post("/test/modelattribute")
                .param("id", "1")
                .param("name", "binghe"))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("id").value(1))
            .andExpect(jsonPath("name").value("binghe"));
    }

    @DisplayName("@ModelAttribute - 요청 매개변수가 없다면 null (기본 타입의 경우 초깃값)으로 설정된다. (Validation을 사용하지 않을 경우)")
    @Test
    void modelAttributeEmpty() throws Exception {
        mockMvc.perform(post("/test/modelattribute")
                .param("name", "binghe"))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("id").isEmpty())
            .andExpect(jsonPath("name").value("binghe"));
    }
}