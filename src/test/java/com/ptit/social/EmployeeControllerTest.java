package com.ptit.social;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ptit.social.model.employee.EmployeeResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class EmployeeControllerTest {
    @Autowired
    private MockMvc mockMvc;
    // Test Case Success
    @Test
    public void getAllEmployeeSuccess() throws Exception{
        MvcResult result = mockMvc.perform(get("/api/v1/employee/"))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andReturn();

        List<EmployeeResponse> responses = new ObjectMapper().readValue(
                result.getResponse().getContentAsString(),
                new TypeReference<List<EmployeeResponse>>() {});
        assertThat(responses).isNotNull();
    }

    @Test
    public void getEmployeeAtAddressCorrect() throws Exception{
        String province = "Tỉnh Hà Giang";
        String district = "Huyện Xín Mần";
        String ward = "Xã Nàn Xỉn";

        MvcResult result = mockMvc.perform(get("/api/v1/employee/full")
                .param("province", province)
                .param("district", district)
                .param("ward", ward))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andReturn();

        List<EmployeeResponse> responses = new ObjectMapper().readValue(
                result.getResponse().getContentAsString(),
                new TypeReference<List<EmployeeResponse>>() {});
        assertThat(responses.get(0).getName()).isEqualTo("e");
    }

    @Test
    public void getEmployeeAtSingleAddress() throws Exception{
        String address = "Huyện Xín Mần";

        MvcResult result = mockMvc.perform(get("/api/v1/employee")
                .param("address", address))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andReturn();

        List<EmployeeResponse> responses = new ObjectMapper().readValue(
                result.getResponse().getContentAsString(),
                new TypeReference<List<EmployeeResponse>>() {});
        assertThat(responses.get(0).getName()).isEqualTo("e");
    }

    @Test
    public void getEmployeeAtAddressHaveNone() throws Exception{
        String province = "Tỉnh Hà Giang";
        String district = "Huyện Xín Mần";
        String ward = "Xã Trung Thịnh";

        MvcResult result = mockMvc.perform(get("/api/v1/employee/full")
                .param("province", province)
                .param("district", district)
                .param("ward", ward))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andReturn();

        List<EmployeeResponse> responses = new ObjectMapper().readValue(
                result.getResponse().getContentAsString(),
                new TypeReference<List<EmployeeResponse>>() {});

        assertThat(responses).isEmpty();
    }

    // Test Case Failed
    @Test
    public void getEmployeeAtMissingWard() throws Exception{
        String province = "Tỉnh Hà Giang";
        String district = "Huyện Xín Mần";
        String ward = "";

        mockMvc.perform(get("/api/v1/employee/full")
                .param("province", province)
                .param("district", district)
                .param("ward", ward))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().string(containsString("Missing parameters")));
    }

    @Test
    public void getEmployeeAtMissingDistrict() throws Exception{
        String province = "Tỉnh Hà Giang";
        String district = "";
        String ward = "Xã Trung Thịnh";

        mockMvc.perform(get("/api/v1/employee/full")
                .param("province", province)
                .param("district", district)
                .param("ward", ward))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().string(containsString("Missing parameters")));
    }

    @Test
    public void getEmployeeAtMissingProvince() throws Exception{
        String province = "";
        String district = "Huyện Xín Mần";
        String ward = "Xã Trung Thịnh";

        mockMvc.perform(get("/api/v1/employee/full")
                .param("province", province)
                .param("district", district)
                .param("ward", ward))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().string(containsString("Missing parameters")));
    }

    @Test
    public void getEmployeeAtWrongAddressFormat() throws Exception{
        String address = "-";

        mockMvc.perform(get("/api/v1/employee")
            .param("address", address))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().string(containsString("Not a valid address")));
    }

    @Test
    public void getEmployeeAtEmptyAddress() throws Exception{
        String address = "";

        mockMvc.perform(get("/api/v1/employee")
                .param("address", address))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().string(containsString("Missing address")));
    }
}
