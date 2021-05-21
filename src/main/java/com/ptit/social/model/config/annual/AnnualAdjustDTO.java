package com.ptit.social.model.config.annual;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AnnualAdjustDTO {
    private float adjustment;
    private int year;
}
