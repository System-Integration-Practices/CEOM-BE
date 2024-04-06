package com.example.ceom.model.sqlserver;


import jakarta.persistence.*;
import lombok.*;

@Table(name = "LUONG")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class LUONG {
    @Id
    @Column(name = "ID")
    private int ID;

    @Column(name = "HESOLUONG")
    private double HESOLUONG;

    @Column(name = "LUONGCOBAN")
    private double LUONGCOBAN;

    @Column(name = "SONGAYCONG")
    private int SONGAYCONG;

    @Column(name = "THANG")
    private int THANG;

    @Column(name = "NAM")
    private int NAM;

    @Column(name = "LUONGTHUCLINH")
    private double LUONGTHUCLINH;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MANHANVIEN")
    private NHANVIEN nhanvien;

    @Override
    public String toString() {
        return "LUONG{" +
                "ID=" + ID +
                ", HESOLUONG=" + HESOLUONG +
                ", LUONGCOBAN=" + LUONGCOBAN +
                ", SONGAYCONG=" + SONGAYCONG +
                ", THANG=" + THANG +
                ", NAM=" + NAM +
                ", LUONGTHUCLINH=" + LUONGTHUCLINH +
                '}';
    }
}
