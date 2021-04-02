package com.ptit.social.model.address.district;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DistrictDTO implements Serializable {
    @JsonProperty("id")
    private int id;
    @JsonProperty("district_name")
    private String name;
}
