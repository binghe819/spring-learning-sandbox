package com.binghe.method_arguments;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
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
class BindingResultControllerTest {

    @Autowired
    private BindingResultController bindingResultController;
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(bindingResultController).build();
    }

    @Test
    void dependency() {
        assertThat(bindingResultController).isNotNull();
        assertThat(mockMvc).isNotNull();
    }

    @DisplayName("BindingResult(잘못된 데이터타입) - @ModelAttribute에 바인딩하는 과정에서 발생하는 에러를 BindingResult를 통해 처리할 수 있다.")
    @Test
    void bindingResultModelAttribute() throws Exception {
        mockMvc.perform(post("/test/binding/modelattribute")
                .param("id", "wrong data"))
            .andDo(print())
            .andExpect(status().isBadRequest());
    }

    @DisplayName("BindingResult(validation) - @ModelAttribute에 바인딩하는 과정에서 Validation의 에러를 BindingResult를 통해 처리할 수 있다.")
    @Test
    void bindingResultModelAttributeWithValidation() throws Exception {
        mockMvc.perform(post("/test/binding/modelattribute")
                .param("id", "0")
                .param("name", "binghe"))
            .andDo(print())
            .andExpect(status().isBadRequest());
    }
}