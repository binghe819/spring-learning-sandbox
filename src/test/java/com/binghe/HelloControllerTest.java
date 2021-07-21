package com.binghe;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = AppConfig.class)
class HelloControllerTest {

    @Autowired
    private HelloController helloController;

    @Autowired
    private ObjectMapper objectMapper;

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

    @DisplayName("get - @RequestParam")
    @Test
    void hello() throws Exception {
        // given
        String msg = "binghe";

        // when, then
        mockMvc
            .perform(
                get("/hello")
                .queryParam("name", msg))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("message").value(msg));
    }

    @DisplayName("post - @RequestBody")
    @Test
    void helloPost() throws Exception {
        // given
        String msg = "binghe";
        Request request = new Request(msg);
        String requestJson = objectMapper.writeValueAsString(request);

        // when, then
        mockMvc
            .perform(
                post("/hello")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("message").value(msg));
    }
}
