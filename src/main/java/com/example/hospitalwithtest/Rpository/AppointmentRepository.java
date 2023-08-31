package com.example.hospitalwithtest.Rpository;

import com.example.hospitalwithtest.Model.Appointment;
import com.example.hospitalwithtest.Model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment,Integer> {
    Appointment findAppointmentById(Integer id);
    Appointment findAppointmentByIdAndPatient(Integer id,Patient patient);

    Appointment findAppointmentByUsernameAndPatient(String username,Patient patient);

    @Query("select a from Appointment a where a.doctorName=?1 and a.patient=?2")
    Appointment AppointmentListByDoctorName(String doctorName,Patient patient);

    Appointment findAppointmentByClinicNameAndPatient(String clinicName,Patient patient);
    Appointment findAppointmentByEmailAndPatient(String email,Patient patient);
    List<Appointment>findAllByPatient(Patient patient);




}
