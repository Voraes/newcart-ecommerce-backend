package com.voraes.newcartbackend.service.Impl;

import com.voraes.newcartbackend.DTO.UserDTO;
import com.voraes.newcartbackend.entity.Address;
import com.voraes.newcartbackend.entity.Order;
import com.voraes.newcartbackend.entity.User;
import com.voraes.newcartbackend.repository.UserRepository;
import com.voraes.newcartbackend.service.AuthService;
import com.voraes.newcartbackend.util.JwtUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Service
public class AuthServiceImpl implements AuthService {


    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    private final JwtUtils jwtUtils;

    public AuthServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JwtUtils jwtUtils) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
    }


    @Override
    public void registerUser(@Valid UserDTO userDTO) {

        List<Address> addresses = new ArrayList<>();
        List<Order> orders = new ArrayList<>();

        User newUser = new User();
        newUser.setUsername(userDTO.getUsername());
        newUser.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        newUser.setEmail(userDTO.getEmail());
        newUser.setRole("USER");
        newUser.setAddresses(addresses);
        newUser.setOrders(orders);

        userRepository.save(newUser);
    }

    @Override
    public void loginUser(UserDTO userDTO) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                userDTO.getUsername(), userDTO.getPassword()
        );

        Authentication authentication = authenticationManager.authenticate(token);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        jwtUtils.generateToken(authentication);
    }
}
