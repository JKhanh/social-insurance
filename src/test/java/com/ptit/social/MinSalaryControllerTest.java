package com.ptit.social;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ptit.social.model.config.minSalary.MinSalary;
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
import org.springframework.transaction.annotation.Transactional;

import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class MinSalaryControllerTest {
    @Autowired
    private MockMvc mockMvc;

    private MinSalary minSalary;

    @Test
    @Order(1)
    public void getMinSalary() throws Exception{
        MvcResult result = mockMvc.perform(get("/api/v1/minsalary/"))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andReturn();

        List<MinSalary> response = new ObjectMapper().readValue(
                result.getResponse().getContentAsString(),
                new TypeReference<List<MinSalary>>() {});

        assertThat(response.size()).isEqualTo(8);
    }

    private void prepareData() throws Exception{
        MvcResult result = mockMvc.perform(get("/api/v1/minsalary/"))
                .andReturn();

        List<MinSalary> response = new ObjectMapper().readValue(
                result.getResponse().getContentAsString(),
                new TypeReference<List<MinSalary>>() {});

        minSalary = response.get(3);
    }

    @Test
    @Order(2)
    @Transactional
    public void updateSuccess() throws Exception{
        prepareData();
        minSalary.setSalary(1200000);

        MvcResult result = mockMvc.perform(put("/api/v1/minsalary/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(minSalary)))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andReturn();

        MinSalary response = new ObjectMapper().readValue(
                result.getResponse().getContentAsString(StandardCharsets.UTF_8),
                new TypeReference<MinSalary>() {});

        assertThat(response.getId()).isEqualTo(minSalary.getId());
        assertThat(response.getArea()).isEqualTo(minSalary.getArea());
        assertThat(response.getType()).isEqualTo(minSalary.getType());
        assertThat(response.getSalary()).isEqualTo(minSalary.getSalary());
    }

    @Test
    @Order(3)
    public void updateWrongIDSmallThan0() throws Exception{
        prepareData();
        int id = minSalary.getId();
        minSalary.setId(-1);

        mockMvc.perform(put("/api/v1/minsalary/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(minSalary)))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().string(
                        containsString("ID " + minSalary.getId() + " not found")));
        minSalary.setId(id);
    }

    @Test
    @Order(4)
    public void updateWrongIDGreaterThan8() throws Exception{
        prepareData();
        int id = minSalary.getId();
        minSalary.setId(9);

        mockMvc.perform(put("/api/v1/minsalary/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(minSalary)))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().string(
                        containsString("ID " + minSalary.getId() + " not found")));
        minSalary.setId(id);
    }

    @Test
    @Order(5)
    public void updateAreaMissing() throws Exception{
        prepareData();
        String area = minSalary.getArea();
        minSalary.setArea("");

        mockMvc.perform(put("/api/v1/minsalary/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(minSalary)))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().string(
                        containsString("Area is empty")));
        minSalary.setArea(area);
    }

    @Test
    @Order(6)
    public void updateTypeSmallerThan1() throws Exception{
        prepareData();
        int type = minSalary.getType();
        minSalary.setType(0);

        mockMvc.perform(put("/api/v1/minsalary/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(minSalary)))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().string(
                        containsString("Wrong type")));
        minSalary.setType(type);
    }

    @Test
    @Order(7)
    public void updateTypeGreaterThan2() throws Exception{
        prepareData();
        int type = minSalary.getType();
        minSalary.setType(3);

        mockMvc.perform(put("/api/v1/minsalary/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(minSalary)))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().string(
                        containsString("Wrong type")));
        minSalary.setType(type);
    }

    @Test
    @Order(8)
    public void updateSalaryNegative() throws Exception{
        prepareData();
        float salary = minSalary.getSalary();
        minSalary.setSalary(-10000);

        mockMvc.perform(put("/api/v1/minsalary/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(minSalary)))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().string(
                        containsString("Wrong salary")));
        minSalary.setSalary(salary);
    }

    private String toJson(MinSalary minSalary){
        try{
            return new ObjectMapper().writeValueAsString(minSalary);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "";
    }
}
