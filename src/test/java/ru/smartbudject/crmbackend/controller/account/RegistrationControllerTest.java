package ru.smartbudject.crmbackend.controller.account;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import ru.smartbudject.crmbackend.AbstractMainTest;
import ru.smartbudject.crmbackend.CommonUtils;
import ru.smartbudject.crmbackend.model.entity.Account;
import ru.smartbudject.crmbackend.repository.AccountRepository;


class RegistrationControllerTest extends AbstractMainTest {


    @Autowired
    private AccountRepository accountRepository;

    @Test
    @DisplayName("Registration test")
    public void testShouldRegistrationUser() throws Exception {
        String json = CommonUtils.getJsonFromResource("controller/registration/RequestRegistration.json");

        mockMvc.perform(
                        MockMvcRequestBuilders
                                .post("/api/user/registration")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(json)
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status()
                        .isOk());

        final List<Account> all = accountRepository.findAll();
        Assertions.assertNotNull(all);
        Assertions.assertEquals(1, all.size());
    }


    @Test
    @DisplayName("Dont using repeated email")
    public void testShouldNotRegistrationUserWhereUsingRepeatEmail() throws Exception {
        String json = CommonUtils.getJsonFromResource("controller/registration/RequestRegistration.json");

        mockMvc.perform(
                        MockMvcRequestBuilders
                                .post("/api/user/registration")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(json)
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status()
                        .isOk());

        mockMvc.perform(
                        MockMvcRequestBuilders
                                .post("/api/user/registration")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(json)
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status()
                        .isOk());

        final List<Account> all = accountRepository.findAll();
        Assertions.assertNotNull(all);
        Assertions.assertEquals(1, all.size());
    }

}