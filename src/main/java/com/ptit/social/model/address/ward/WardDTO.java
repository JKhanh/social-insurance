package com.ptit.social.model.address.ward;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WardDTO implements Serializable {
    @JsonProperty("id")
    private int id;
    @JsonProperty("ward_name")
    private String name;
}
