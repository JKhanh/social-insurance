package com.ptit.social.model.address.provice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Province implements Serializable {
    @Id
    private int id;
    private String name;
    private String type;
}
