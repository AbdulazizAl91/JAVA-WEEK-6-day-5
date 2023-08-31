package com.example.hospitalwithtest.Controller;

import com.example.hospitalwithtest.Api.ApiResponse;
import com.example.hospitalwithtest.Model.AdmissionEmployee;
import com.example.hospitalwithtest.Service.AdmissionEmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admission-employee")
@RequiredArgsConstructor
public class AdmissionEmployeeController {
    private final AdmissionEmployeeService admissionEmployeeService;

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid AdmissionEmployee admissionEmployee){
        admissionEmployeeService.register(admissionEmployee);
        return ResponseEntity.status(200).body(new ApiResponse("admissionEmployee registered"));
    }

    @PutMapping("/update")
    public ResponseEntity updateAdmissionEmployee(@AuthenticationPrincipal AdmissionEmployee admissionEmployee, @RequestBody @Valid AdmissionEmployee admissionEmployee_info){
        admissionEmployeeService.updateAdmissionEmployee(admissionEmployee.getId(),admissionEmployee_info);
        return ResponseEntity.status(200).body(new ApiResponse("admissionEmployee updated"));
    }
    @DeleteMapping("/delete")
    public ResponseEntity deleteAdmissionEmployee(@AuthenticationPrincipal AdmissionEmployee admissionEmployee){
        admissionEmployeeService.deleteAdmissionEmployee(admissionEmployee.getId());
        return ResponseEntity.status(200).body(new ApiResponse("admissionEmployee deleted"));

    }
}
