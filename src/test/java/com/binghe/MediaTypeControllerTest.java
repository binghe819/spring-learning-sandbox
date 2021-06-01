package com.binghe;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
@ContextConfiguration(classes = WebConfig.class)
class MediaTypeControllerTest {

    @Autowired
    private MediaTypeController mediaTypeController;
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(mediaTypeController).build();
    }

    @Test
    void dependency() {
        assertThat(mediaTypeController).isNotNull();
        assertThat(mockMvc).isNotNull();
    }

    @DisplayName("consume(성공 케이스) - 핸들러에 consume을 JSON으로 해두면 요청 Content-Type이 JSON인 것만 처리할 수 있다.")
    @Test
    void consume_success() throws Exception {
        mockMvc.perform(get("/mediatype/test/consume").contentType(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isOk());
    }

    @DisplayName("consume(실패 케이스) - 핸들러의 consume과 일치하지 않는 요청이라면 415(Unsupported Media Type)을 던진다.")
    @Test
    void consume_failure() throws Exception {
        mockMvc.perform(get("/mediatype/test/consume"))
            .andDo(print())
            .andExpect(status().is(415));
    }

    @DisplayName("produces(성공 케이스) - 핸들러에 producef를 JSON으로 해두면 요청 Accept가 JSON인 것만 처리할 수 있다.")
    @Test
    void produces_success() throws Exception {
        mockMvc.perform(get("/mediatype/test/produce").accept(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isOk());
    }

    @DisplayName("produces(실패 케이스) - 핸들러의 produces과 일치하지 않는 요청이라면 406(Not Acceptable )을 던진다.")
    @Test
    void produces_failure() throws Exception {
        mockMvc.perform(get("/mediatype/test/produce").accept(MediaType.TEXT_HTML))
            .andDo(print())
            .andExpect(status().is(406));
    }

    @DisplayName("produces(팁) - Accept가 비어있다면 아무거나 다 받겠다는 의미가 되어 핸들러가 처리할 수 있다.")
    @Test
    void produces_edge_case() throws Exception {
        mockMvc.perform(get("/mediatype/test/produce"))
            .andDo(print())
            .andExpect(status().isOk());
    }
}