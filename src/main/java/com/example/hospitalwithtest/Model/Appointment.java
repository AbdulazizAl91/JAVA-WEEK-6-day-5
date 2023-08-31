package com.example.hospitalwithtest.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotEmpty
    @Column(columnDefinition = "varchar(20) not null ")
    private String patient_name;
    @NotEmpty
    @Column(columnDefinition = "varchar(20) not null unique")
    private String username;
    @NotEmpty
    @Pattern(regexp ="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{6,16}$" )
    @Column(columnDefinition = "varchar(16) not null")
    private String password;
    @NotEmpty
    @Email
    @Column(columnDefinition = "varchar(40) not null unique")
    private String email;
    @NotEmpty
    @Column(columnDefinition = "varchar(20) not null")
    private String doctorName;
    @NotEmpty
    @Column(columnDefinition = "varchar(20) not null")
    private String clinicName;

    @ManyToOne
    @JoinColumn(name = "Patient_id",referencedColumnName = "user_id")
    @JsonIgnore
    private Patient patient;

}
