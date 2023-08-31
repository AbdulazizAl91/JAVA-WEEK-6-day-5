package com.example.hospitalwithtest.Rpository;

import com.example.hospitalwithtest.Model.AdmissionEmployee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdmissionEmployeeRepository extends JpaRepository<AdmissionEmployee,Integer> {
        AdmissionEmployee findAdmissionEmployeeById(Integer id);
}
