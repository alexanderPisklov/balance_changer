package ru.caramba.balancechange.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.caramba.balancechange.controllers.BalanceControllerTestData.REST_URL_GET_EXISTED_WALLET;
import static ru.caramba.balancechange.controllers.BalanceControllerTestData.REST_URL_GET_NOT_EXISTED_WALLET;

@SpringBootTest
@Transactional
@AutoConfigureMockMvc
@ActiveProfiles("db")
class BalanceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    protected ResultActions perform(MockHttpServletRequestBuilder builder) throws Exception {
        return mockMvc.perform(builder);
    }

    @Test
    void getExistedBalanceTest() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL_GET_EXISTED_WALLET))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().json(BalanceControllerTestData.getWalletJson()));
    }

    @Test
    void getNotExistedBalanceTest() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL_GET_NOT_EXISTED_WALLET))
                .andExpect(status().isNotFound())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

}