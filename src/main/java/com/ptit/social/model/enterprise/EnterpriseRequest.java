package com.ptit.social.model.enterprise;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EnterpriseRequest implements Serializable {
    @JsonProperty("name")
    private String name;

    @JsonProperty("type")
    private String type;
}
