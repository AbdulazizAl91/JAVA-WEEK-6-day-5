package com.example.hospitalwithtest.Service;

import com.example.hospitalwithtest.Api.ApiException;
import com.example.hospitalwithtest.Model.User;
import com.example.hospitalwithtest.Rpository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByUsername(username);
        if (user==null){
            throw new ApiException("Wrong username or password");
        }

        return user;
    }
}
