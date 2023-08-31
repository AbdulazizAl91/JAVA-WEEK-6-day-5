package com.example.hospitalwithtest.Confing;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class ConfigurationSecurity {
    private final UserDetailsService userDetailsService;

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        daoAuthenticationProvider.setPasswordEncoder(new BCryptPasswordEncoder());
        return daoAuthenticationProvider;

    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http.csrf().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                .and()
                .authenticationProvider(daoAuthenticationProvider())
                .authorizeHttpRequests()
                .requestMatchers(HttpMethod.POST,"/api/v1/patient/register").permitAll()
                .requestMatchers(HttpMethod.PUT,"/api/v1/patient/update").hasAnyAuthority("PATIENT","ADMIN")
                .requestMatchers(HttpMethod.DELETE,"/api/v1/patient/delete").hasAnyAuthority("PATIENT","ADMIN")
                .requestMatchers(HttpMethod.GET,"/api/v1/appointment/get").hasAuthority("PATIENT")
                .requestMatchers(HttpMethod.GET,"/api/v1/appointment/get-all").hasAuthority("ADMIN")
                .requestMatchers(HttpMethod.POST,"/api/v1/appointment/add").hasAuthority("PATIENT")
                .requestMatchers(HttpMethod.PUT,"/api/v1/appointment/update/{id}").hasAuthority("PATIENT")
                .requestMatchers(HttpMethod.DELETE,"/api/v1/appointment/delete/{id}").hasAuthority("PATIENT")
                .requestMatchers(HttpMethod.GET,"/api/v1/appointment/search-by-id/{id}").hasAuthority("PATIENT")
                .requestMatchers(HttpMethod.GET,"/api/v1/appointment/search-by-username/{username}").hasAuthority("PATIENT")
                .requestMatchers(HttpMethod.GET,"/api/v1/appointment/search-by-doctor-name/{doctorName}").hasAnyAuthority("PATIENT","ADMIN")
                .requestMatchers(HttpMethod.GET,"/api/v1/appointment/search-by-clinic-name/{clinicName}").hasAnyAuthority("PATIENT","ADMIN")
                .requestMatchers(HttpMethod.GET,"/api/v1/appointment//search-by-email/{email}").hasAnyAuthority("PATIENT","ADMIN")
                .requestMatchers(HttpMethod.POST,"/api/v1/clinic-employee/register").hasAuthority("ADMIN")
                .requestMatchers(HttpMethod.PUT,"/api/v1/clinic-employee/update").hasAuthority("ADMIN")
                .requestMatchers(HttpMethod.DELETE,"/api/v1/clinic-employee/delete").hasAuthority("ADMIN")
                .requestMatchers(HttpMethod.GET,"/api/v1/clinic/get").hasAuthority("CLINIC")
                .requestMatchers(HttpMethod.GET,"/api/v1/clinic/get-all").hasAuthority("CLINIC")
                .requestMatchers(HttpMethod.POST,"/api/v1/clinic/add").hasAuthority("CLINIC")
                .requestMatchers(HttpMethod.PUT,"/api/v1/clinic/update/{id}").hasAuthority("CLINIC")
                .requestMatchers(HttpMethod.DELETE,"/api/v1/clinic/delete/{id}").hasAuthority("CLINIC")
                .requestMatchers(HttpMethod.GET,"/api/v1/clinic/search-by-id/{id}").hasAuthority("CLINIC")
                .requestMatchers(HttpMethod.GET,"/api/v1/clinic/search-by-name/{name}").hasAuthority("CLINIC")
                .requestMatchers(HttpMethod.GET,"/api/v1/clinic/search-by-patient-name/{PatientName}").hasAuthority("CLINIC")
                .requestMatchers(HttpMethod.GET,"/api/v1/clinic/search-by-doctorName/{doctorName}").hasAuthority("CLINIC")
                .requestMatchers(HttpMethod.GET,"/api/v1/clinic/search-by-price/{price}").hasAuthority("CLINIC")
                .requestMatchers(HttpMethod.POST,"/api/v1/admission-employee/register").hasAuthority("ADMIN")
                .requestMatchers(HttpMethod.PUT,"/api/v1/admission-employee/update").hasAuthority("ADMIN")
                .requestMatchers(HttpMethod.DELETE,"/api/v1/admission-employee/delete").hasAuthority("ADMIN")
                .requestMatchers(HttpMethod.GET,"/api/v1/admission/get-all").hasAnyAuthority("ADMISSION","ADMIN")
                .requestMatchers(HttpMethod.GET,"/api/v1/admission/get").hasAuthority("ADMISSION")
                .requestMatchers(HttpMethod.POST,"/api/v1/admission/add").hasAuthority("ADMISSION")
                .requestMatchers(HttpMethod.PUT,"/api/v1/admission/update/{id}").hasAuthority("ADMISSION")
                .requestMatchers(HttpMethod.DELETE,"/api/v1/admission/delete/{id}").hasAuthority("ADMISSION")
                .requestMatchers(HttpMethod.GET,"/api/v1/admission/search-by-id/{id}").hasAuthority("ADMISSION")
                .requestMatchers(HttpMethod.GET,"/api/v1/admission/search-by-patient-name/{patientName}").hasAuthority("ADMISSION")
                .requestMatchers(HttpMethod.GET,"/api/v1/admission/search-by-doctor-name/{doctorName}").hasAuthority("ADMISSION")
                .requestMatchers(HttpMethod.GET,"/api/v1/admission/search-by-patient-bed-number/{patientBedNumber}").hasAuthority("ADMISSION")
                .anyRequest().authenticated()
                .and()
                .logout().logoutUrl("api/v1/auth/logout")
                .deleteCookies("JSESSIONID")
                .invalidateHttpSession(true)
                .and()
                .httpBasic();
        return http.build();


    }
}
