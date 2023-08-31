package com.example.hospitalwithtest.Controller;

import com.example.hospitalwithtest.Api.ApiResponse;
import com.example.hospitalwithtest.Model.Admission;
import com.example.hospitalwithtest.Model.AdmissionEmployee;
import com.example.hospitalwithtest.Service.AdmissionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admission")
@RequiredArgsConstructor
public class AdmissionController {
    private final AdmissionService admissionService;
    @GetMapping("/get-all")
    public ResponseEntity getAll(){
       return ResponseEntity.status(200).body(admissionService.getAll());
    }
    @GetMapping("/get")
    public ResponseEntity getMyAdmission(@AuthenticationPrincipal AdmissionEmployee admissionEmployee){
        return ResponseEntity.status(200).body(admissionService.getMyAdmission(admissionEmployee.getId()));
    }
    @PostMapping("/add")
    public ResponseEntity addAdmission(@AuthenticationPrincipal AdmissionEmployee admissionEmployee,@RequestBody @Valid Admission admission){
        admissionService.addAdmission(admissionEmployee.getId(),admission);
        return ResponseEntity.status(200).body(new ApiResponse("admission add"));
    }
    @PutMapping("/update/{id}")
    public ResponseEntity updateAdmission(@AuthenticationPrincipal AdmissionEmployee admissionEmployee ,@PathVariable Integer id, @RequestBody @Valid Admission admission){
        admissionService.updateAdmission(admissionEmployee.getId(),id,admission);
        return ResponseEntity.status(200).body(new ApiResponse("admission updated"));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteAdmission(@AuthenticationPrincipal AdmissionEmployee admissionEmployee,@PathVariable Integer id){
        admissionService.deleteAdmission(admissionEmployee.getId(),id);
        return ResponseEntity.status(200).body(new ApiResponse("admission deleted"));
    }
    @GetMapping("/search-by-id/{id}")
    public ResponseEntity findAdmissionById(@PathVariable Integer id){

        return ResponseEntity.status(200).body(admissionService.findAdmissionById(id));
    }
    @GetMapping("/search-by-patient-name/{patientName}")
    public ResponseEntity findAdmissionByPatientName(@PathVariable String patientName){

        return ResponseEntity.status(200).body(admissionService.findAdmissionByPatientName(patientName));
    }
    @GetMapping("/search-by-doctor-name/{doctorName}")
    public ResponseEntity findAdmissionByDoctorName(@PathVariable String doctorName){

        return ResponseEntity.status(200).body(admissionService.findAdmissionByDoctorName(doctorName));
    }
    @GetMapping("/search-by-patient-bed-number/{patientBedNumber}")
    public ResponseEntity wherePatientBedNumber(@PathVariable Integer patientBedNumber){
        return ResponseEntity.status(200).body(admissionService.wherePatientBedNumber(patientBedNumber));
    }


}
