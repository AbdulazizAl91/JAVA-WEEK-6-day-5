package com.example.hospitalwithtest.Rpository;

import com.example.hospitalwithtest.Model.ClinicEmployee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClinicEmployeeRepository extends JpaRepository<ClinicEmployee,Integer> {
    ClinicEmployee findClinicEmployeeById(Integer id);
}
