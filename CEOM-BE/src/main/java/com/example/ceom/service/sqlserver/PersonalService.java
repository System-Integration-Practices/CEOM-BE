package com.example.ceom.service.sqlserver;

import com.example.ceom.entity.sqlserver.Personal;
import com.example.ceom.model.request.CreatePersonalRequest;

public interface PersonalService {
    Personal savePersonal(CreatePersonalRequest request);
    void deletePersonal(int personalId);
    Personal updatePersonal(int id, CreatePersonalRequest request);
}
