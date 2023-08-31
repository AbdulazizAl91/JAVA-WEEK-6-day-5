package com.example.hospitalwithtest.Controller;

import com.example.hospitalwithtest.Api.ApiResponse;
import com.example.hospitalwithtest.Model.Appointment;
import com.example.hospitalwithtest.Model.Patient;
import com.example.hospitalwithtest.Service.AppointmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/appointment")
@RequiredArgsConstructor
public class AppointmentController {
    private final AppointmentService appointmentService;

    @GetMapping("/get-all")
    public ResponseEntity getAll(){
        return ResponseEntity.status(200).body(appointmentService.getAll());
    }
    @GetMapping("/get")
    public ResponseEntity getMyAdmission(@AuthenticationPrincipal Patient patient){
        return ResponseEntity.status(200).body(appointmentService.getMyAppointment(patient.getId()));
    }
    @PostMapping("/add")
    public ResponseEntity addAppointment(@AuthenticationPrincipal Patient patient,@RequestBody @Valid Appointment appointment){
        appointmentService.addAppointment(patient.getId(),appointment);
        return ResponseEntity.status(200).body(new ApiResponse("appointment add"));
    }
    @PutMapping("/update/{id}")
    public ResponseEntity updateAppointment(@AuthenticationPrincipal Patient patient,@PathVariable Integer id, @RequestBody @Valid Appointment appointment){
        appointmentService.updateAppointment(patient.getId(),id,appointment);
        return ResponseEntity.status(200).body(new ApiResponse("appointment updated"));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteAppointment(@AuthenticationPrincipal Patient patient,@PathVariable Integer id){
        appointmentService.deleteAppointment(patient.getId(), id);
        return ResponseEntity.status(200).body(new ApiResponse("appointment deleted"));
    }
    @GetMapping("/search-by-id/{id}")
    public ResponseEntity findAppointmentById(@AuthenticationPrincipal Patient patient,@PathVariable Integer id){

        return ResponseEntity.status(200).body(appointmentService.findAppointmentById(id, patient.getId()));
    }
    @GetMapping("/search-by-username/{username}")
    public ResponseEntity findAppointmentByUsername(@AuthenticationPrincipal Patient patient,@PathVariable String username){

        return ResponseEntity.status(200).body(appointmentService.findAppointmentByUsername(username, patient.getId()));
    }
    @GetMapping("/search-by-doctor-name/{doctorName}")
    public ResponseEntity AppointmentListByDoctorName(@AuthenticationPrincipal Patient patient,@PathVariable String doctorName){
        Appointment appointments= appointmentService.AppointmentListByDoctorName(doctorName, patient.getId());
        return ResponseEntity.status(200).body(appointments);
    }
    @GetMapping("/search-by-clinic-name/{clinicName}")
    public ResponseEntity findAppointmentByClinicName(@AuthenticationPrincipal Patient patient,@PathVariable String clinicName){
        Appointment appointments= appointmentService.findAppointmentByClinicName(clinicName,patient.getId());
        return ResponseEntity.status(200).body(appointments);
    }
    @GetMapping("/search-by-email/{email}")
    public ResponseEntity findAppointmentByEmail(@AuthenticationPrincipal Patient patient,@PathVariable String email){

        return ResponseEntity.status(200).body(appointmentService.findAppointmentByEmail(email, patient.getId()));
    }

}
