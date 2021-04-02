package com.ptit.social.model.address.ward;

import com.ptit.social.model.address.district.District;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Ward implements Serializable {
    @Id
    private int id;
    private String name;
    private String type;
    @ManyToOne
    @JoinColumn(name = "district_id", referencedColumnName = "id")
    private District district;
}
