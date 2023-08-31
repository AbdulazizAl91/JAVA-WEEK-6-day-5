package com.example.hospitalwithtest.Rpository;

import com.example.hospitalwithtest.Model.Clinic;
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
class ClinicRepositoryTest {
    @Autowired
    ClinicRepository clinicRepository;
    Clinic clinic;
    Clinic clinic1;
    Clinic clinic2;
    List<Clinic>clinics;

    @BeforeEach
    void setUp() {
        clinic= new Clinic(null,"aziz","neurology","khaild",300,null);
        clinic1= new Clinic(null,"saad","neurology","khaild",300,null);
    }

    @Test
    void findClinicByPrice() {
        clinicRepository.save(clinic);
        clinicRepository.save(clinic1);
       clinics= clinicRepository.findClinicByPrice(clinic.getPrice());
        Assertions.assertThat(clinics.get(0).getPrice()).isEqualTo(clinic.getPrice());
    }
    @Test
    void findClinicByPatientName() {
        clinicRepository.save(clinic);
        clinic2 = clinicRepository.findClinicByPatientName(clinic.getPatientName());
        Assertions.assertThat(clinic2).isEqualTo(clinic);

    }
}