package com.example.hospitalwithtest.Controller;

import com.example.hospitalwithtest.Api.ApiResponse;
import com.example.hospitalwithtest.Model.ClinicEmployee;
import com.example.hospitalwithtest.Service.ClinicEmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/clinic-employee")
@RequiredArgsConstructor
public class ClinicEmployeeController {
    private final ClinicEmployeeService clinicEmployeeService;

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid ClinicEmployee clinicEmployee){
        clinicEmployeeService.register(clinicEmployee);
        return ResponseEntity.status(200).body(new ApiResponse("admissionEmployee registered"));
    }

    @PutMapping("/update")
    public ResponseEntity updateAdmissionEmployee(@AuthenticationPrincipal ClinicEmployee clinicEmployee, @RequestBody @Valid ClinicEmployee clinicEmployee_info){
        clinicEmployeeService.updateClinicEmployee(clinicEmployee.getId(),clinicEmployee_info);
        return ResponseEntity.status(200).body(new ApiResponse("admissionEmployee updated"));
    }
    @DeleteMapping("/delete")
    public ResponseEntity deleteAdmissionEmployee(@AuthenticationPrincipal ClinicEmployee clinicEmployee){
        clinicEmployeeService.deleteClinicEmployee(clinicEmployee.getId());
        return ResponseEntity.status(200).body(new ApiResponse("admissionEmployee deleted"));

    }
}
