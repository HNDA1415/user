package com.example.usertest.service;

import com.example.usertest.entity.UserEntity;
import com.example.usertest.exception.BadRequestException;
import com.example.usertest.repo.UserRepository;
import com.example.usertest.security.UserPrincipal;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;



@Service
@NoArgsConstructor
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String mobileNumber) throws UsernameNotFoundException {

        UserEntity user = userRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(()->new BadRequestException("MobileNumber not found!"));

        return UserPrincipal.build(user);



    }
}
