package com.leovegas.digital.wallet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.leovegas.digital.wallet.data.dto.transaction.CreateTransactionDto;
import com.leovegas.digital.wallet.data.dto.transaction.TransactionDto;
import com.leovegas.digital.wallet.data.enumeration.TransactionType;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TransactionTestController {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    public static TransactionDto debitTransactionDto;
    private static TransactionDto creditTransactionDto;


    @Test
    @Order(1)
    public void createDebitTransactionDto() throws Exception {
        CreateTransactionDto createTransactionDto = new CreateTransactionDto();
        createTransactionDto.setTransactionId(100l);
        createTransactionDto.setAmount(10);
        createTransactionDto.setPlayerId(PlayerTestController.playerDto.getId());
        createTransactionDto.setType(TransactionType.DEBIT);

        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders
                .post("/v1/transaction")
                .content(asJsonString(createTransactionDto))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.jsonPath("$.transactionId").exists())
                .andDo(print());

        MvcResult result = resultActions.andReturn();
        String contentAsString = result.getResponse().getContentAsString();
        debitTransactionDto = objectMapper.readValue(contentAsString, TransactionDto.class);
    }

    @Test
    @Order(2)
    public void createCreditTransactionDto() throws Exception {
        CreateTransactionDto createTransactionDto = new CreateTransactionDto();
        createTransactionDto.setTransactionId(200l);
        createTransactionDto.setAmount(10);
        createTransactionDto.setPlayerId(PlayerTestController.playerDto.getId());
        createTransactionDto.setType(TransactionType.CREDIT);

        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders
                .post("/v1/transaction")
                .content(asJsonString(createTransactionDto))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.jsonPath("$.transactionId").exists())
                .andDo(print());

        MvcResult result = resultActions.andReturn();
        String contentAsString = result.getResponse().getContentAsString();
        creditTransactionDto = objectMapper.readValue(contentAsString, TransactionDto.class);
    }


    @Test
    @Order(3)
    public void getTransaction() throws Exception {
        mockMvc.perform(get("/v1/transaction/{id}", creditTransactionDto.getTransactionId()))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @Order(5)
    public void getAllTransactions() throws Exception {
        mockMvc.perform(get("/v1/transaction/"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @Order(6)
    public void deleteTransaction() throws Exception {
        mockMvc.perform(delete("/v1/transaction/{id}", debitTransactionDto.getTransactionId()))
                .andExpect(status().isNoContent())
                .andDo(print());
    }


    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}