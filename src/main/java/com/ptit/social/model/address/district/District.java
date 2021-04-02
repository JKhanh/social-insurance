package com.ptit.social.model.address.district;

import com.ptit.social.model.address.provice.Province;
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
public class District implements Serializable {
    @Id
    private int id;
    private String name;
    private String type;
    @ManyToOne
    @JoinColumn(name = "province_id", referencedColumnName = "id")
    private Province province;
}
