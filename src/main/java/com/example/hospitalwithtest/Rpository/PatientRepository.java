package com.example.hospitalwithtest.Rpository;

import com.example.hospitalwithtest.Model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient,Integer> {
    Patient findPatientById(Integer id);
}
