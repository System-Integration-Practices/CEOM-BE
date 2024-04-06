package com.example.ceom.repository.mysql;

import com.example.ceom.model.mysql.nhanvien;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface nhanvienRepository extends JpaRepository<nhanvien, Integer> {
    @Query("SELECT a FROM nhanvien a JOIN FETCH a.phongban")
    List<nhanvien> fetchAll();
}
