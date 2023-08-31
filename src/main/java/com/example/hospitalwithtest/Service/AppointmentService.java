package com.example.hospitalwithtest.Service;

import com.example.hospitalwithtest.Api.ApiException;
import com.example.hospitalwithtest.Model.Appointment;
import com.example.hospitalwithtest.Model.Patient;
import com.example.hospitalwithtest.Rpository.AppointmentRepository;
import com.example.hospitalwithtest.Rpository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AppointmentService {
    private final AppointmentRepository appointmentRepository;
    private final PatientRepository patientRepository;

    public List<Appointment> getAll(){
       return appointmentRepository.findAll();
    }
    public List<Appointment> getMyAppointment(Integer patient_id){
        Patient patient =patientRepository.findPatientById(patient_id);
        return appointmentRepository.findAllByPatient(patient);
    }

    public void addAppointment(Integer patient_id ,Appointment appointment){
        Patient patient =patientRepository.findPatientById(patient_id);
        appointment.setPatient(patient);
        appointmentRepository.save(appointment);
    }


    public void updateAppointment(Integer patient_id,Integer id,Appointment appointment){
        Patient patient =patientRepository.findPatientById(patient_id);
        Appointment appointment1=appointmentRepository.findAppointmentById(id);
        if (appointment1 !=null && appointment1.getPatient().equals(patient)){
            appointment1.setPatient_name(appointment.getPatient_name());
            appointment1.setUsername(appointment.getUsername());
            appointment1.setPassword(appointment.getPassword());
            appointment1.setEmail(appointment.getEmail());
            appointment1.setDoctorName(appointment.getDoctorName());
            appointment1.setClinicName(appointment.getClinicName());
            appointmentRepository.save(appointment1);
        }
        else throw new ApiException("appointment not founded");

    }
    public void deleteAppointment(Integer patient_id,Integer id){
        Patient patient =patientRepository.findPatientById(patient_id);
        Appointment appointment=appointmentRepository.findAppointmentById(id);
        if (appointment !=null && appointment.getPatient().equals(patient)){
            appointmentRepository.delete(appointment);
        }

        else throw new ApiException("appointment not founded");
    }
    public Appointment findAppointmentById(Integer id,Integer patient_id){
        Patient patient=patientRepository.findPatientById(patient_id);
        Appointment appointment = appointmentRepository.findAppointmentByIdAndPatient(id,patient);
        if (appointment !=null && appointment.getPatient().equals(patient)){
            return appointment;

        }
        else throw new ApiException("id not found");
    }
    public Appointment findAppointmentByUsername(String username, Integer patient_id){
        Patient patient=patientRepository.findPatientById(patient_id);
        Appointment appointment = appointmentRepository.findAppointmentByUsernameAndPatient(username,patient);
        if (appointment !=null && appointment.getPatient().equals(patient)){
            return appointment;

        }
        else throw new ApiException("check your username or password");

    }
    public Appointment AppointmentListByDoctorName(String doctorName,Integer patient_id){
        Patient patient=patientRepository.findPatientById(patient_id);
        Appointment appointments = appointmentRepository.AppointmentListByDoctorName(doctorName,patient);
        if (appointments !=null && appointments.getPatient().equals(patient)){

            return appointments;
        }
        throw new ApiException("no appointments in this clinic with doctor "+doctorName);
    }
    public Appointment findAppointmentByClinicName(String clinicName ,Integer patient_id){
        Patient patient=patientRepository.findPatientById(patient_id);
        Appointment appointments = appointmentRepository.findAppointmentByClinicNameAndPatient(clinicName,patient);
        if (appointments !=null && appointments.getPatient().equals(patient)){
            return appointments;

        }
        throw new ApiException("no appointments in this clinic "+clinicName);

    }
    public Appointment findAppointmentByEmail(String email,Integer patient_id){
        Patient patient1=patientRepository.findPatientById(patient_id);
        Appointment appointment = appointmentRepository.findAppointmentByEmailAndPatient(email,patient1);
        if (appointment !=null && appointment.getPatient().equals(patient1)){
            return appointment;
        }
        else throw new ApiException("check your username or password");

    }




}
