package IO.Egen.controller;


import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK
)
@AutoConfigureMockMvc
@ActiveProfiles("integrationtest")
public class vehicleControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void findAll() throws Exception {
//        mockMvc.perform(MockMvcRequestBuilders.get("/vehicles"))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)));
    }

    @Test
    public void findByID() {
    }

    @Test
    public void findHighAlerts() {
    }

    @Test
    public void findVehicleAlerts() {
    }

    @Test
    public void findGeoLocation() {
    }

    @Test
    public void create() {
    }

    @Test
    public void update() {
    }
}