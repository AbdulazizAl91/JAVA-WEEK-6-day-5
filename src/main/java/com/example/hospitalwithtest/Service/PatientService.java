package com.example.hospitalwithtest.Service;

import com.example.hospitalwithtest.Model.Patient;
import com.example.hospitalwithtest.Rpository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PatientService {
    private final PatientRepository patientRepository;

    public void register(Patient patient){
        String hash= new BCryptPasswordEncoder().encode(patient.getPassword());
        patient.setPassword(hash);


        patientRepository.save(patient);
    }
    public void updatePatient(Integer patient_id,Patient patient_info){
        Patient patient = patientRepository.findPatientById(patient_id);
        String hash= new BCryptPasswordEncoder().encode(patient.getPassword());
        patient.setUsername(patient_info.getUsername());
        patient.setPassword(hash);
        patientRepository.save(patient);

    }
    public void deleteUser(Integer patient_id){
        Patient patient = patientRepository.findPatientById(patient_id);
        patientRepository.delete(patient);
    }

}
