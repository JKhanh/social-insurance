package com.ptit.social.model.employee;

import com.ptit.social.model.enterprise.Enterprise;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Table(name = "employee")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
public class Employee implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    private String name;
    private Date birthday;
    private Date joinDate;
    private String position;
    @ManyToOne
    @JoinColumn(name = "enterprise_id", referencedColumnName = "id")
    private Enterprise enterprise;
    private String address;
    private String street;
    private Long salary;
}
