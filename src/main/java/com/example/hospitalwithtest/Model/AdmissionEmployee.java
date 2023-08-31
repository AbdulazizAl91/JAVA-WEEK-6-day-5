package com.example.hospitalwithtest.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class AdmissionEmployee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String username;
    private String  password;
    private String role;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "admissionEmployee")
    private Set<Admission> admissions;


    @OneToOne
    @MapsId
    @JsonIgnore
    private User user;


}
