package com.ptit.social.model.config.minSalary;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "min_salary")
public class MinSalary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String area;
    int type;
    float salary;
}
