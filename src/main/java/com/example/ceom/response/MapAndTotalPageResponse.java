package com.example.ceom.response;

import com.example.ceom.dto.EmployeeDTO;
import lombok.*;

import java.util.Map;

@Getter
@Setter
@Builder
@NoArgsConstructor
public class MapAndTotalPageResponse {
    private Map<Integer, EmployeeDTO> employeeDTOMap;
    private int totalPage;

    public MapAndTotalPageResponse(Map<Integer, EmployeeDTO> employeeDTOMap, int totalPage) {
        this.employeeDTOMap = employeeDTOMap;
        this.totalPage = totalPage;
    }
}