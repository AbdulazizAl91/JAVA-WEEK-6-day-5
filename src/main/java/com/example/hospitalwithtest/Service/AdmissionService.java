package com.example.hospitalwithtest.Service;

import com.example.hospitalwithtest.Api.ApiException;
import com.example.hospitalwithtest.Model.Admission;
import com.example.hospitalwithtest.Model.AdmissionEmployee;
import com.example.hospitalwithtest.Rpository.AdmissionEmployeeRepository;
import com.example.hospitalwithtest.Rpository.AdmissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdmissionService {
    private final AdmissionRepository admissionRepository;
    private final AdmissionEmployeeRepository admissionEmployeeRepository;

    public List<Admission> getAll(){
        return admissionRepository.findAll();
    }
    public List<Admission>getMyAdmission(Integer admissionEmployee_id){
        AdmissionEmployee admissionEmployee = admissionEmployeeRepository.findAdmissionEmployeeById(admissionEmployee_id);
        return admissionRepository.findAllByAdmissionEmployee(admissionEmployee);
    }
    public void addAdmission(Integer admissionEmployee_id ,Admission admission){
        AdmissionEmployee admissionEmployee = admissionEmployeeRepository.findAdmissionEmployeeById(admissionEmployee_id);
        admission.setAdmissionEmployee(admissionEmployee);
        admissionRepository.save(admission);
    }
    public void updateAdmission(Integer admissionEmployee_id,Integer id,Admission admission){
        AdmissionEmployee admissionEmployee = admissionEmployeeRepository.findAdmissionEmployeeById(admissionEmployee_id);
        Admission admission1= admissionRepository.findAdmissionById(id);
        if (admission1 !=null && admission1.getAdmissionEmployee().equals(admissionEmployee)){
            admission1.setNumberOfBedsCapacity(admission.getNumberOfBedsCapacity());
            admission1.setPatientName(admission.getPatientName());
            admission1.setPatientBedNumber(admission.getPatientBedNumber());
            admission1.setDoctorName(admission.getDoctorName());
            admissionRepository.save(admission1);
        }
         throw new ApiException("admission not founded");

    }

    public void deleteAdmission(Integer id,Integer admissionEmployee_id){
        AdmissionEmployee admissionEmployee = admissionEmployeeRepository.findAdmissionEmployeeById(admissionEmployee_id);
        Admission admission1= admissionRepository.findAdmissionById(id);
        if (admission1 !=null && admission1.getAdmissionEmployee().equals(admissionEmployee)){

            admissionRepository.delete(admission1);
        }
        else throw new ApiException("admission not founded");

    }

    public Admission findAdmissionById(Integer id){
        Admission admission = admissionRepository.findAdmissionById(id);
        if (admission==null){
            throw new ApiException("id not found");
        }
        return admission;
    }
    public Admission findAdmissionByPatientName(String patientName){
        Admission admission = admissionRepository.findAdmissionByPatientName(patientName);
        if (admission==null){
            throw new ApiException("id not found");
        }
        return admission;
    }
    public Admission wherePatientBedNumber(Integer patientBedNumber){
        Admission admission = admissionRepository.wherePatientBedNumber(patientBedNumber);
        if (admission==null){
            throw new ApiException("id not found");
        }
        return admission;
    }
    public List<Admission> findAdmissionByDoctorName(String doctorName){
        List<Admission> admissions = admissionRepository.findAdmissionByDoctorName(doctorName);
        if (admissions==null){
            throw new ApiException("is empty");
        }
        return admissions;
    }




}
