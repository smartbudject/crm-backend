package ru.smartbudject.crmbackend.controller.pointsales;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import ru.smartbudject.crmbackend.AbstractMainTest;
import ru.smartbudject.crmbackend.CommonUtils;
import ru.smartbudject.crmbackend.EntityUtils;
import ru.smartbudject.crmbackend.model.entity.Account;
import ru.smartbudject.crmbackend.model.entity.PointSales;
import ru.smartbudject.crmbackend.repository.PointSalesRepository;


public class PointSalesControllerTest extends AbstractMainTest {



    @Autowired
    private PointSalesRepository pointSalesRepository;


    @Test
    @DisplayName("add point sales")
    @WithMockUser(roles = "ADMIN")
    @Transactional
    public void testShouldAddPointSalesInAccount() throws Exception {
        Mockito.when(userService.tryGetAuthenticated())
                .thenReturn(entityUtils.getUser(2L));

        String addJson = CommonUtils.getJsonFromResource("controller/pointsales/RequestAddNewPointSales.json");

        mockMvc.perform(
                        MockMvcRequestBuilders
                                .post("/api/point-sales")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(addJson)
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status()
                        .isOk());
    }


    @Test
    @DisplayName("update point sales")
    @WithMockUser(roles = "ADMIN")
    @Transactional
    public void testShouldUpdatePointSalesInAccount() throws Exception {
        final Optional<Account> user = entityUtils.getUser(2L);
        Mockito.when(userService.tryGetAuthenticated())
                .thenReturn(user);
        final PointSales pointSales = entityUtils.getPointSales(user.get());
        String updateJson = CommonUtils.getJsonFromResource("controller/pointsales/UpdatePointSalesRequest.json");


        final ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.put("/api/point-sales/" + pointSales.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updateJson))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status()
                        .isOk());

        Long id = Long.parseLong(resultActions.andReturn()
                .getResponse()
                .getContentAsString());

        final PointSales pointSalesSaved = pointSalesRepository.findById(id)
                .orElse(null);
        Assertions.assertNotNull(pointSalesSaved);
        Assertions.assertNotEquals("Moscow", pointSalesSaved.getAddress());
        Assertions.assertNotEquals("Point 1", pointSalesSaved.getName());
    }

}
