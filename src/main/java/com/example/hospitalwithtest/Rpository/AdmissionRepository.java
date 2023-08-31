package com.example.hospitalwithtest.Rpository;

import com.example.hospitalwithtest.Model.Admission;
import com.example.hospitalwithtest.Model.AdmissionEmployee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface AdmissionRepository extends JpaRepository<Admission,Integer> {
    Admission findAdmissionById(Integer id);



    Admission findAdmissionByPatientName(String patientName);

    @Query("select p from Admission p where p.patientBedNumber=?1 and p.admissionEmployee=?2")
    Admission wherePatientBedNumber(Integer patientBedNumber);

    List<Admission>findAdmissionByDoctorName(String doctorName);
    List<Admission>findAllByAdmissionEmployee(AdmissionEmployee admissionEmployee);

}
