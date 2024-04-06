package com.example.ceom.model.sqlserver;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Table(name = "NHANVIEN")
@Data
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class NHANVIEN {
    @Id
    @Column(name = "MANHANVIEN")
    private int MANHANVIEN;
    @Column(name = "HO")
    private String HO;
    @Column(name = "TEN")
    private String TEN;
    @Column(name = "NGAYSINH")
    private LocalDate NGAYSINH;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "nhanvien", orphanRemoval = true)
    List<LUONG> luongs = new ArrayList<>();
}
