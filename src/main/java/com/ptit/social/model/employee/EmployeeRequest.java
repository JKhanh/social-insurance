package com.ptit.social.model.employee;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.ptit.social.model.enterprise.EnterpriseRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeRequest implements Serializable {
    @JsonProperty("name")
    private String name;
    @JsonProperty("birthday")
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date birthday;
    @JsonProperty("join_date")
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date joinDate;
    @JsonProperty("position")
    private String position;
    @JsonProperty("enterprise")
    private EnterpriseRequest enterprise;
    @JsonProperty("address")
    private String address;
    @JsonProperty("street")
    private String street;
    @JsonProperty("salary")
    private Long salary;
}
