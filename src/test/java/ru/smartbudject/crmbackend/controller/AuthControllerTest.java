package ru.smartbudject.crmbackend.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import ru.smartbudject.crmbackend.AbstractMainTest;
import ru.smartbudject.crmbackend.CommonUtils;


public class AuthControllerTest extends AbstractMainTest {

    @Test
    @DisplayName("Get jwt token")
    public void getJwtToken() throws Exception {
        String registrationJson = CommonUtils.getJsonFromResource("controller/registration/RequestRegistration.json");

        mockMvc.perform(
                        MockMvcRequestBuilders
                                .post("/api/user/registration")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(registrationJson)
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status()
                        .isOk());


        String authJson = CommonUtils.getJsonFromResource("controller/registration/AuthRequest.json");

        mockMvc.perform(
                        MockMvcRequestBuilders
                                .post("/api/user/sing-in")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(authJson)
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status()
                        .isOk());
    }

}
