package com.ptit.social.model.enterprise;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "enterprise")
@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Enterprise implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    private String name;
    private String type;
}
