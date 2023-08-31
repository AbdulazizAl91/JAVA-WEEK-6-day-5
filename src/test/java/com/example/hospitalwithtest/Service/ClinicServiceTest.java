package com.example.hospitalwithtest.Service;

import com.example.hospitalwithtest.Model.Admission;
import com.example.hospitalwithtest.Model.Clinic;
import com.example.hospitalwithtest.Model.ClinicEmployee;
import com.example.hospitalwithtest.Rpository.ClinicEmployeeRepository;
import com.example.hospitalwithtest.Rpository.ClinicRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ClinicServiceTest {
    @InjectMocks
    ClinicService clinicService;
    @Mock
    ClinicRepository clinicRepository;
    @Mock
    ClinicEmployeeRepository clinicEmployeeRepository;
    Clinic clinic;
    Clinic clinic1;
    Clinic clinic2;
    List<Clinic>clinics;
    ClinicEmployee clinicEmployee;

    @BeforeEach
    void setUp() {
        clinicEmployee = new ClinicEmployee(1,"aziz","12345671Aa","USER",null,null);
        clinic= new Clinic(null,"aziz","neurology","khaild",300,null);
        clinic1= new Clinic(null,"saad","neurology","khaild",300,null);
        clinics=new ArrayList<>();
        clinics.add(clinic);
        clinics.add(clinic1);
    }

    @Test
    void getAll() {
        when(clinicRepository.findAll()).thenReturn(clinics);

        List<Clinic> clinicList=clinicService.getAll();
        Assertions.assertEquals(clinicList,clinics);
        Mockito.verify(clinicRepository,times(1)).findAll();
    }

    @Test
    void getMyClinic() {
        when(clinicRepository.findAllByClinicEmployee(clinicEmployee)).thenReturn(clinics);
        when(clinicEmployeeRepository.findClinicEmployeeById(clinicEmployee.getId())).thenReturn(clinicEmployee);

        List<Clinic> clinicList=clinicService.getMyClinic(clinicEmployee.getId());
        Assertions.assertEquals(clinicList,clinics);
        verify(clinicRepository,times(1)).findAllByClinicEmployee(clinicEmployee);
        verify(clinicEmployeeRepository,times(1)).findClinicEmployeeById(clinicEmployee.getId());
    }

    @Test
    void addClinic() {
        when(clinicEmployeeRepository.findClinicEmployeeById(clinicEmployee.getId())).thenReturn(clinicEmployee);
        clinicService.addClinic(clinicEmployee.getId(),clinic);

        verify(clinicRepository,times(1)).save(clinic);
        verify(clinicEmployeeRepository,times(1)).findClinicEmployeeById(clinicEmployee.getId());
    }
}