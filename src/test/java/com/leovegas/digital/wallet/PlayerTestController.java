package com.leovegas.digital.wallet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.leovegas.digital.wallet.data.dto.player.CreatePlayerDto;
import com.leovegas.digital.wallet.data.dto.player.PlayerDto;
import com.leovegas.digital.wallet.data.dto.player.UpdatePlayerDto;
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
class PlayerTestController {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    public static PlayerDto playerDto;
    private static PlayerDto deletePlayerDto;


    @Test
    @Order(1)
    public void createPlayer() throws Exception {
        CreatePlayerDto createPlayerDto = new CreatePlayerDto();
        createPlayerDto.setFirstName("Test First Name");
        createPlayerDto.setLastName("Test Last Name");
        createPlayerDto.setUserName("TestUserName");
        createPlayerDto.setOpeningBalance(10l);

        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders
                .post("/v1/player")
                .content(asJsonString(createPlayerDto))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
                .andDo(print());

        MvcResult result = resultActions.andReturn();
        String contentAsString = result.getResponse().getContentAsString();
        playerDto = objectMapper.readValue(contentAsString, PlayerDto.class);
    }

    @Test
    @Order(2)
    public void createPlayerForDelete() throws Exception {
        CreatePlayerDto createPlayerDto = new CreatePlayerDto();
        createPlayerDto.setFirstName("Temp First Name");
        createPlayerDto.setLastName("Temp Last Name");
        createPlayerDto.setUserName("TempUserName");
        createPlayerDto.setOpeningBalance(10);

        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders
                .post("/v1/player")
                .content(asJsonString(createPlayerDto))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
                .andDo(print());

        MvcResult result = resultActions.andReturn();
        String contentAsString = result.getResponse().getContentAsString();
        deletePlayerDto = objectMapper.readValue(contentAsString, PlayerDto.class);
    }

    @Test
    @Order(3)
    public void updatePlayer() throws Exception {
        UpdatePlayerDto updatePlayerDto = new UpdatePlayerDto();
        updatePlayerDto.setId(playerDto.getId());
        updatePlayerDto.setFirstName("Updated Test First Name");
        updatePlayerDto.setLastName("Updated Test Last Name");
        updatePlayerDto.setUserName("UpdatedTestUserName");
        updatePlayerDto.setBalance(100);

        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders
                .put("/v1/player")
                .content(asJsonString(updatePlayerDto))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
                .andDo(print());

        MvcResult result = resultActions.andReturn();
        String contentAsString = result.getResponse().getContentAsString();
        playerDto = objectMapper.readValue(contentAsString, PlayerDto.class);
    }

    @Test
    @Order(4)
    public void getPlayer() throws Exception {
        mockMvc.perform(get("/v1/player/{id}", playerDto.getId()))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @Order(5)
    public void getAllPlayers() throws Exception {
        mockMvc.perform(get("/v1/player/"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @Order(6)
    public void deletePlayer() throws Exception {
        mockMvc.perform(delete("/v1/player/{id}", deletePlayerDto.getId()))
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