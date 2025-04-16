package ru.smartbudject.crmbackend.controller;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import ru.smartbudject.crmbackend.AbstractMainTest;
import ru.smartbudject.crmbackend.CommonUtils;
import ru.smartbudject.crmbackend.model.entity.Account;


public class ProductControllerTest extends AbstractMainTest {

    @Test
    @DisplayName("add product")
    @WithMockUser(roles = "ADMIN")
    @Transactional
    public void testShouldAddPointSalesInAccount() throws Exception {
        final Optional<Account> user = entityUtils.getUser(2L);
        Mockito.when(userService.tryGetAuthenticated())
                .thenReturn(user);
        entityUtils.getPointSales(user.orElseThrow(IllegalStateException::new));

        String addJson = CommonUtils.getJsonFromResource("controller/product/RequestAddProduct.json");

        mockMvc.perform(
                        MockMvcRequestBuilders
                                .post("/api/product")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(addJson)
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status()
                        .isOk());
    }
}
