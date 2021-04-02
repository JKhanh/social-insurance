package com.ptit.social.model.address.provice;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProvinceDTO implements Serializable {
    @JsonProperty("id")
    private int id;
    @JsonProperty("province_name")
    private String name;
}
