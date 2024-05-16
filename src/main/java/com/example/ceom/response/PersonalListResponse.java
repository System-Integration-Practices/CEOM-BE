package com.example.ceom.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@Data
@Builder
@NoArgsConstructor
public class PersonalListResponse {
    private List<PersonalResponse> personals;
    private int totalPages;
}
