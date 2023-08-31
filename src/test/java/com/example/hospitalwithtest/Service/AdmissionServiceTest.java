package com.example.hospitalwithtest.Service;

import com.example.hospitalwithtest.Model.Admission;
import com.example.hospitalwithtest.Model.AdmissionEmployee;
import com.example.hospitalwithtest.Rpository.AdmissionEmployeeRepository;
import com.example.hospitalwithtest.Rpository.AdmissionRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AdmissionServiceTest {
    @InjectMocks
    AdmissionService admissionService;
    @Mock
    AdmissionRepository admissionRepository;
    @Mock
    AdmissionEmployeeRepository admissionEmployeeRepository;

    AdmissionEmployee admissionEmployee;
    Admission admission1;
    Admission admission2;
    Admission admission3;

    List<Admission> admissions;

    @BeforeEach
    void setUp() {
        admissionEmployee = new AdmissionEmployee(1,"aziz","12345678Aa","ADMISSION", null,null);
        admission1= new Admission(null,4,"aziz",2,"khalid",null);
        admission2= new Admission(null,4,"saad",1,"khalid",null);
        admission3= new Admission(null,4,"salem",2,"khalid",null);
        admissions =new ArrayList<>();
        admissions.add(admission1);
        admissions.add(admission2);
        admissions.add(admission3);

    }

    @Test
    void getMyAdmission() {
        when(admissionRepository.findAllByAdmissionEmployee(admissionEmployee)).thenReturn(admissions);
        when(admissionEmployeeRepository.findAdmissionEmployeeById(admissionEmployee.getId())).thenReturn(admissionEmployee);

        List<Admission> admissionList=admissionService.getMyAdmission(admissionEmployee.getId());
        Assertions.assertEquals(admissionList,admissions);
        verify(admissionRepository,times(1)).findAllByAdmissionEmployee(admissionEmployee);
        verify(admissionEmployeeRepository,times(1)).findAdmissionEmployeeById(admissionEmployee.getId());

    }

    @Test
    void addAdmission() {
        when(admissionEmployeeRepository.findAdmissionEmployeeById(admissionEmployee.getId())).thenReturn(admissionEmployee);
        admissionService.addAdmission(admissionEmployee.getId(),admission1);

        verify(admissionRepository,times(1)).save(admission1);
        verify(admissionEmployeeRepository,times(1)).findAdmissionEmployeeById(admissionEmployee.getId());

    }

}