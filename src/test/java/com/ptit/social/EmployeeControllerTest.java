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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class EmployeeControllerTest {
    @Autowired
    private MockMvc mockMvc;

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

        assertThat(responses.size()).isEqualTo(0);
    }

    @Test
    public void getEmployeeAtMissingWard() throws Exception{
        String province = "Tỉnh Hà Giang";
        String district = "Huyện Xín Mần";

        mockMvc.perform(get("/api/v1/employee/full")
                .param("province", province)
                .param("district", district))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(status().reason(containsString("parameter 'ward' is not present")));
    }

    @Test
    public void getEmployeeAtMissingDistrict() throws Exception{
        String province = "Tỉnh Hà Giang";
        String ward = "Xã Trung Thịnh";

        mockMvc.perform(get("/api/v1/employee/full")
                .param("province", province)
                .param("ward", ward))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(status().reason(containsString("parameter 'district' is not present")));
    }

    @Test
    public void getEmployeeAtMissingProvince() throws Exception{
        String district = "Huyện Xín Mần";
        String ward = "Xã Trung Thịnh";

        mockMvc.perform(get("/api/v1/employee/full")
                .param("district", district)
                .param("ward", ward))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(status().reason(containsString("parameter 'province' is not present")));
    }
}
