package com.example.hospitalwithtest.Controller;

import com.example.hospitalwithtest.Api.ApiResponse;
import com.example.hospitalwithtest.Model.Clinic;
import com.example.hospitalwithtest.Model.ClinicEmployee;
import com.example.hospitalwithtest.Service.ClinicService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/clinic")
@RequiredArgsConstructor

public class ClinicController {
    private final ClinicService clinicService;
    @GetMapping("/get-all")
    public ResponseEntity getAll(){
        return ResponseEntity.status(200).body(clinicService.getAll());
    }
    @GetMapping("/get")
    public ResponseEntity getMyClinic(@AuthenticationPrincipal ClinicEmployee clinicEmployee){
        return ResponseEntity.status(200).body(clinicService.getMyClinic(clinicEmployee.getId()));
    }
    @PostMapping("/add")
    public ResponseEntity addClinic(@AuthenticationPrincipal ClinicEmployee clinicEmployee,@RequestBody @Valid Clinic clinic){
        clinicService.addClinic(clinicEmployee.getId(),clinic);
        return ResponseEntity.status(200).body(new ApiResponse("Clinic added"));
    }
    @PutMapping("/update/{id}")
    public ResponseEntity updateClinic(@AuthenticationPrincipal ClinicEmployee clinicEmployee,@PathVariable Integer id,@RequestBody @Valid Clinic clinic){
        clinicService.updateClinic(clinicEmployee.getId(),id,clinic);
        return ResponseEntity.status(200).body(new ApiResponse("clinic updated"));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteClinic(@AuthenticationPrincipal ClinicEmployee clinicEmployee,@PathVariable Integer id){
        clinicService.deleteClinic(clinicEmployee.getId(),id);
        return ResponseEntity.status(200).body(new ApiResponse("clinic deleted"));
    }
    @GetMapping("/search-by-id/{id}")
    public ResponseEntity findClinicById(@PathVariable Integer id){

        return ResponseEntity.status(200).body(clinicService.findClinicById(id));
    }
    @GetMapping("/search-by-name/{name}")
    public ResponseEntity findClinicByName(@PathVariable String name){
        List<Clinic> clinics= clinicService.findClinicByName(name);
        return ResponseEntity.status(200).body(clinics);
    }
    @GetMapping("/search-by-patient-name/{PatientName}")
    public ResponseEntity findClinicByPatientName(@PathVariable String PatientName){

        return ResponseEntity.status(200).body(clinicService.findClinicByPatientName(PatientName));
    }
    @GetMapping("/search-by-doctorName/{doctorName}")
    public ResponseEntity whereDoctor_name(@PathVariable String doctorName){
        List<Clinic> clinics= clinicService.whereDoctor_name(doctorName);
        return ResponseEntity.status(200).body(clinics);
    }
    @GetMapping("/search-by-price/{price}")
    public ResponseEntity findClinicByPrice(@PathVariable Integer price){

        return ResponseEntity.status(200).body(clinicService.findClinicByPrice(price));
    }





}
