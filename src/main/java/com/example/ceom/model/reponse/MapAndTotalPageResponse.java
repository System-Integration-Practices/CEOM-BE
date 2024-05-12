package com.example.ceom.model.reponse;

import com.example.ceom.dto.EmployeeDTO;
import com.example.ceom.dto.ListPersonDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@Builder
@NoArgsConstructor
public class MapAndTotalPageResponse {
    private Map<Integer, ListPersonDTO> personDTOMap;
    private int totalPage;

    public MapAndTotalPageResponse(Map<Integer, ListPersonDTO> personDTOMap, int totalPage) {
        this.personDTOMap = personDTOMap;
        this.totalPage = totalPage;
    }
}