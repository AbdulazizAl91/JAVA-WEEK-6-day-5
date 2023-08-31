package com.example.hospitalwithtest.Service;

import com.example.hospitalwithtest.Model.AdmissionEmployee;
import com.example.hospitalwithtest.Rpository.AdmissionEmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdmissionEmployeeService {
    private final AdmissionEmployeeRepository admissionEmployeeRepository;

    public void register(AdmissionEmployee admissionEmployee){
        String hash= new BCryptPasswordEncoder().encode(admissionEmployee.getPassword());
        admissionEmployee.setPassword(hash);


        admissionEmployeeRepository.save(admissionEmployee);
    }
    public void updateAdmissionEmployee(Integer admissionEmployee_id, AdmissionEmployee admissionEmployee_info){
        AdmissionEmployee admissionEmployee = admissionEmployeeRepository.findAdmissionEmployeeById(admissionEmployee_id);
        String hash= new BCryptPasswordEncoder().encode(admissionEmployee.getPassword());
        admissionEmployee.setUsername(admissionEmployee.getUsername());
        admissionEmployee.setPassword(hash);
        admissionEmployeeRepository.save(admissionEmployee);

    }
    public void deleteAdmissionEmployee(Integer admissionEmployee_id){
        AdmissionEmployee admissionEmployee = admissionEmployeeRepository.findAdmissionEmployeeById(admissionEmployee_id);
        admissionEmployeeRepository.delete(admissionEmployee);
    }
}
