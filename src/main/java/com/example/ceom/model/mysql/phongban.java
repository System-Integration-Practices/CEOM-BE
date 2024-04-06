package com.example.ceom.model.mysql;


import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Table(name = "phongban")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class phongban implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "MAPHONGBAN")
    private Integer MAPHONGBAN;

    @Column(name = "TENPHONGBAN", length = 100)
    private String TENPHONGBAN;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "phongban", orphanRemoval = true)
    List<nhanvien> nhanviens = new ArrayList<>();

    @Override
    public String toString() {
        return "phongban{" +
                "MAPHONGBAN=" + MAPHONGBAN +
                ", TENPHONGBAN='" + TENPHONGBAN + '\'' +
                '}';
    }
}
