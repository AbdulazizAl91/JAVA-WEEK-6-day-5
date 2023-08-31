package com.example.hospitalwithtest.Rpository;

import com.example.hospitalwithtest.Model.Admission;
import com.example.hospitalwithtest.Model.AdmissionEmployee;
import com.example.hospitalwithtest.Model.Appointment;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class AdmissionRepositoryTest {
    @Autowired
    AdmissionRepository admissionRepository;


    Admission admission1;
    Admission admission2;
    Admission admission3;


    Admission admission;
    List<Admission> admissions;


    @BeforeEach
    void setUp() {

        admission1= new Admission(1,4,"aziz",2,"khalid",null);
        admission2= new Admission(1,4,"saad",1,"khalid",null);
        admission3= new Admission(1,4,"salem",2,"khalid",null);

    }

    @Test
    void findAdmissionById() {
        admissionRepository.save(admission1);
        admission = admissionRepository.findAdmissionById(admission1.getId());
        Assertions.assertThat(admission).isEqualTo(admission1);

    }
    @Test
    void findAdmissionByPatientName() {
        admissionRepository.save(admission1);
        admission = admissionRepository.findAdmissionByPatientName(admission1.getPatientName());
        Assertions.assertThat(admission).isEqualTo(admission1);

    }
    @Test
    public void  findAllByUserTest(){
        admissionRepository.save(admission1);
        admissionRepository.save(admission2);
        admissionRepository.save(admission3);
        admissions = admissionRepository.findAdmissionByDoctorName(admission1.getDoctorName());


        Assertions.assertThat(admissions.get(0).getDoctorName()).isEqualTo(admission1.getDoctorName());
    }



}