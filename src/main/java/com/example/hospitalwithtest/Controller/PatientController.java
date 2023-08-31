package com.example.hospitalwithtest.Controller;

import com.example.hospitalwithtest.Api.ApiResponse;
import com.example.hospitalwithtest.Model.Patient;
import com.example.hospitalwithtest.Service.PatientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/patient")
@RequiredArgsConstructor
public class PatientController {
    private final PatientService patientService;
    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid Patient patient){
        patientService.register(patient);
        return ResponseEntity.status(200).body(new ApiResponse("patient registered"));
    }

    @PutMapping("/update")
    public ResponseEntity updatePatient(@AuthenticationPrincipal Patient patient, @RequestBody @Valid Patient patient_info){
        patientService.updatePatient(patient.getId(),patient_info);
        return ResponseEntity.status(200).body(new ApiResponse("patient updated"));
    }
    @DeleteMapping("/delete")
    public ResponseEntity deletePatient(@AuthenticationPrincipal Patient patient){
        patientService.deleteUser(patient.getId());
        return ResponseEntity.status(200).body(new ApiResponse("patient deleted"));

    }

}
