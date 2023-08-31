package com.example.hospitalwithtest.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
//    @NotEmpty(message = "user name should not empty")
//    @Size(min = 4,max = 20,message = "username should be between 4 and 20")
//    @Column(columnDefinition = "varchar(20) not null unique")
    private String username;
//    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,225}$",message = "password should has small letters, capital letters and numbers")
//    @Column(columnDefinition = "varchar(225) not null")
    private String password;
//    @Pattern(regexp = "PATIENT|CLINIC|ADMISSION",message = "chose ADMISSION , CLINIC,PATIENT")
//    @Column(columnDefinition = "varchar(25) not null")
    private String role;

    @OneToOne(cascade = CascadeType.DETACH,mappedBy = "user")
    @PrimaryKeyJoinColumn
    private Patient patient;

    @OneToOne(cascade = CascadeType.DETACH,mappedBy = "user")
    @PrimaryKeyJoinColumn

    private ClinicEmployee clinicEmployee;
    @OneToOne(cascade = CascadeType.DETACH,mappedBy = "user")
    @PrimaryKeyJoinColumn
    private AdmissionEmployee admissionEmployee;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority(this.role));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
