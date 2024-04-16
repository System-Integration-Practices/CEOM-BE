package com.example.ceom.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class MergeDto {
    public Integer MANHANVIEN;

    public String HO;

    public String TEN;

    private String TENPHONGBAN;

    private double LUONGCOBAN;
}
