package com.example.hospitalwithtest.Service;

import com.example.hospitalwithtest.Api.ApiException;
import com.example.hospitalwithtest.Model.Clinic;
import com.example.hospitalwithtest.Model.ClinicEmployee;
import com.example.hospitalwithtest.Rpository.ClinicEmployeeRepository;
import com.example.hospitalwithtest.Rpository.ClinicRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClinicService {
    private final ClinicRepository clinicRepository;
    private final ClinicEmployeeRepository clinicEmployeeRepository;
    public List<Clinic> getAll(){
        return clinicRepository.findAll();
    }

    public List<Clinic> getMyClinic(Integer clinicEmployee_id){
        ClinicEmployee clinicEmployee =clinicEmployeeRepository.findClinicEmployeeById(clinicEmployee_id);
        return clinicRepository.findAllByClinicEmployee(clinicEmployee);
    }
    public void addClinic(Integer clinicEmployee_id ,Clinic clinic){
        ClinicEmployee clinicEmployee =clinicEmployeeRepository.findClinicEmployeeById(clinicEmployee_id);
        clinic.setClinicEmployee(clinicEmployee);
        clinicRepository.save(clinic);
    }
    public void updateClinic(Integer clinicEmployee_id,Integer id,Clinic clinic){
        ClinicEmployee clinicEmployee =clinicEmployeeRepository.findClinicEmployeeById(clinicEmployee_id);
        Clinic clinics = clinicRepository.findClinicById(id);
        if (clinics!=null&& clinics.getClinicEmployee().equals(clinicEmployee)){
            clinics.setPatientName(clinic.getPatientName());
            clinics.setClinicName(clinic.getClinicName());
            clinics.setDoctorName(clinic.getDoctorName());
            clinics.setPrice(clinic.getPrice());


        }
        throw new ApiException("id not founded");

    }
    public void deleteClinic( Integer clinicEmployee_id,Integer id){
        ClinicEmployee clinicEmployee =clinicEmployeeRepository.findClinicEmployeeById(clinicEmployee_id);
        Clinic clinic = clinicRepository.findClinicById(id);
        if (clinic!=null&& clinic.getClinicEmployee().equals(clinicEmployee)){
            clinicRepository.delete(clinic);

        }
        throw new ApiException("id not founded");

    }
    public Clinic findClinicById(Integer id){
        Clinic clinic = clinicRepository.findClinicById(id);
        if (clinic==null){
            throw new ApiException("id not found");
        }
        return clinic;
    }
    public List<Clinic> findClinicByName(String clinicName){
        List<Clinic> clinic = clinicRepository.findClinicByClinicName(clinicName);
        if (clinic==null){
            throw new ApiException("is empty");
        }
        return clinic;
    }
    public Clinic findClinicByPatientName(String patientName){
        Clinic clinic = clinicRepository.findClinicByPatientName(patientName);
        if (clinic==null){
            throw new ApiException("id not found");
        }
        return clinic;
    }

    public List<Clinic> whereDoctor_name(String doctorName){
        List<Clinic> clinic = clinicRepository.whereDoctor_name(doctorName);
        if (clinic==null){
            throw new ApiException("is empty");
        }
        return clinic;
    }
    public List<Clinic> findClinicByPrice(Integer price){
        List<Clinic> clinic = clinicRepository.findClinicByPrice(price);
        if (clinic==null){
            throw new ApiException("is empty");
        }
        return clinic;
    }





}
