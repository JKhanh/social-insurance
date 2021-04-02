package com.ptit.social.model.insurance;

import com.ptit.social.model.employee.Employee;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "insurance")
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Insurance implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "employee_name")
    private String name;
    private InsuranceType type;
    @OneToOne
    @JoinColumn(name = "employee_id", referencedColumnName = "id")
    private Employee employee;
}

