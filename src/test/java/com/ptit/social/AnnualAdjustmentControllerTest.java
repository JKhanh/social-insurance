package com.ptit.social;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ptit.social.model.config.annual.AnnualAdjustDTO;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class AnnualAdjustmentControllerTest {
    @Autowired
    private MockMvc mockMvc;

    private static AnnualAdjustDTO expected;

    @BeforeAll
    public static void setup(){
        expected = AnnualAdjustDTO.builder()
                .adjustment(2)
                .year(2020)
                .build();
    }

    @Test
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

    private String toJson(AnnualAdjustDTO annualAdjustDTO){
        try{
            return new ObjectMapper().writeValueAsString(annualAdjustDTO);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "";
    }
}
