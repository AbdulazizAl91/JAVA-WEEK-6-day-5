package com.example.hospitalwithtest.Controller;

import com.example.hospitalwithtest.Api.ApiResponse;
import com.example.hospitalwithtest.Model.Admission;
import com.example.hospitalwithtest.Model.AdmissionEmployee;
import com.example.hospitalwithtest.Service.AdmissionService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(SpringExtension.class)
@WebMvcTest(value = AdmissionController.class,excludeAutoConfiguration = {SecurityAutoConfiguration.class})
class AdmissionControllerTest {
    @MockBean
    AdmissionService admissionService;
    @Autowired
    MockMvc mockMvc;
    Admission admission1;
    Admission admission2;
    Admission admission3;
    AdmissionEmployee admissionEmployee;
    ApiResponse apiResponse;

    List<Admission> admissions,admissionList;

    @BeforeEach
    void setUp() {
        admissionEmployee = new AdmissionEmployee(1,"aziz","12345678Aa","ADMISSION", null,null);
        admission1= new Admission(1,4,"aziz",2,"khalid",null);
        admission2= new Admission(2,4,"saad",1,"khalid",null);
        admission3= new Admission(3,4,"salem",2,"khalid",null);
        admissions =new ArrayList<>();
        admissions.add(admission1);
        admissions.add(admission2);
        admissions.add(admission3);

    }
    @Test
    void getAll()throws Exception{
        Mockito.when(admissionService.getAll()).thenReturn(admissions);
        mockMvc.perform(get("/api/v1/admission/get-all"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(3)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].patientName").value("aziz"));


    }

    @Test
    void getMyAdmission() throws Exception {
        Mockito.when(admissionService.getMyAdmission(admission1.getId())).thenReturn(admissions);
        mockMvc.perform(get("/api/v1/admission/get"))
                .andExpect(status().isOk());

    }

    @Test
    void addAdmission() throws Exception {
        mockMvc.perform(post("/api/v1/admission/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content( new ObjectMapper().writeValueAsString(admission1)))
                .andExpect(status().isOk());
    }


    @Test
    void deleteAdmission() throws Exception {
        mockMvc.perform(delete("/api/v1/admission/delete/{Id}",admission1.getId()))
                .andExpect(status().isOk());
    }
}