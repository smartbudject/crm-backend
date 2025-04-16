package ru.smartbudject.crmbackend.controller.pointsales;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import ru.smartbudject.crmbackend.AbstractMainTest;
import ru.smartbudject.crmbackend.CommonUtils;
import ru.smartbudject.crmbackend.EntityUtils;


public class PointSalesControllerTest extends AbstractMainTest {

    @Autowired
    private EntityUtils entityUtils;


    @Test
    @DisplayName("add point sales")
    @WithMockUser(roles = "ADMIN")
    @Transactional
    public void testShouldAddPointSalesInAccount() throws Exception {
        Mockito.when(userService.tryGetAuthenticated()).thenReturn(entityUtils.getUser(2L));

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

}
