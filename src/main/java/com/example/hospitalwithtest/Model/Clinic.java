package com.example.hospitalwithtest.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class Clinic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id  ;
    @NotEmpty
    @Column(columnDefinition = "varchar(25) not null")
    private String patientName;
    @NotEmpty
    @Pattern(regexp = "surgery|cardiology|neurology|")
    @Column(columnDefinition = "varchar(20) not null check (clinic_name in('surgery','cardiology','neurology'))")
    private String clinicName;
    @NotEmpty
    @Column(columnDefinition = "varchar(25) not null")
    private String doctorName;
    @Positive
    @Column(columnDefinition = "int not null")
    private Integer price;

    @ManyToOne
    @JoinColumn(name = "clinicEmployee_id",referencedColumnName = "user_id")
    @JsonIgnore
    private ClinicEmployee clinicEmployee;

}
