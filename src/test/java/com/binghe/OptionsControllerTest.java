package com.binghe;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.options;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = WebConfig.class)
class OptionsControllerTest {

    @Autowired
    private OptionsController optionsController;
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(optionsController).build();
    }

    @Test
    void dependency() {
        assertThat(optionsController).isNotNull();
        assertThat(mockMvc).isNotNull();
    }

    @DisplayName("Options요청을 날리면 해당 핸들러가 어떤 요청을 할 수 있는지 응답 헤더의 Allow에 나온다. (핑)")
    @Test
    void optionsTest() throws Exception {
        mockMvc.perform(options("/options/test"))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(header().exists(HttpHeaders.ALLOW))
            .andExpect(header().stringValues(
                HttpHeaders.ALLOW,
                hasItems(
                    containsString("GET"),
                    containsString("HEAD"),
                    containsString("POST"),
                    containsString("OPTIONS"))));
    }
}