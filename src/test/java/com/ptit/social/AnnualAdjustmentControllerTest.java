package com.ptit.social;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ptit.social.model.config.annual.AnnualAdjustDTO;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AnnualAdjustmentControllerTest {
    @Autowired
    private MockMvc mockMvc;

    private static AnnualAdjustDTO expected;

    @BeforeAll
    public static void setup(){
        expected = AnnualAdjustDTO.builder()
                .adjustment(2)
                .year(2021)
                .build();
    }

    @AfterAll
    public static void teardown(){
    }

    @Test
    public void testAddAndGet() throws Exception{
        addSuccess();
        getLatestSuccess();
    }

    public void addSuccess() throws Exception {
        MvcResult result = mockMvc.perform(post("/api/v1/annual/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(expected)))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andReturn();

        AnnualAdjustDTO response = new ObjectMapper().readValue(
                result.getResponse().getContentAsString(),
                new TypeReference<AnnualAdjustDTO>() {});

        assertThat(response.getYear()).isEqualTo(expected.getYear());
        assertThat(response.getAdjustment()).isEqualTo(expected.getAdjustment());
    }

    public void getLatestSuccess() throws Exception{
        MvcResult result = mockMvc.perform(get("/api/v1/annual/"))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andReturn();

        AnnualAdjustDTO response = new ObjectMapper().readValue(
                result.getResponse().getContentAsString(),
                new TypeReference<AnnualAdjustDTO>() {});

        assertThat(response.getYear()).isEqualTo(expected.getYear());
        assertThat(response.getAdjustment()).isEqualTo(expected.getAdjustment());
    }

    private String toJson(AnnualAdjustDTO annualAdjustDTO){
        try{
            return new ObjectMapper().writeValueAsString(annualAdjustDTO);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "";
    }

    @Test
    public void addAdjustmentNegative() throws Exception{
        AnnualAdjustDTO annualAdjustDTO = AnnualAdjustDTO.builder()
                .adjustment(-1)
                .year(1999)
                .build();

        mockMvc.perform(post("/api/v1/annual/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(annualAdjustDTO)))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().string(containsString("Adjustment cannot be negative")));
    }

    @Test
    public void addYearBC() throws Exception{
        AnnualAdjustDTO annualAdjustDTO = AnnualAdjustDTO.builder()
                .adjustment(1)
                .year(-1999)
                .build();

        mockMvc.perform(post("/api/v1/annual/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(annualAdjustDTO)))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().string(containsString("Year cannot be negative")));
    }

    @Test
    public void addAdjustmentMoreThan100() throws Exception{
        AnnualAdjustDTO annualAdjustDTO = AnnualAdjustDTO.builder()
                .adjustment(1000)
                .year(1999)
                .build();

        mockMvc.perform(post("/api/v1/annual/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(annualAdjustDTO)))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().string(containsString("Adjustment cannot be higher than 100%")));
    }
}
