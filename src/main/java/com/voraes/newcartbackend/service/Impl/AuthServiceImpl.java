package com.voraes.newcartbackend.service.Impl;

import com.voraes.newcartbackend.DTO.LoginDTO;
import com.voraes.newcartbackend.DTO.UserDTO;
import com.voraes.newcartbackend.entity.Address;
import com.voraes.newcartbackend.entity.Order;
import com.voraes.newcartbackend.entity.User;
import com.voraes.newcartbackend.repository.UserRepository;
import com.voraes.newcartbackend.service.AuthService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Service
public class AuthServiceImpl implements AuthService {


    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final UserDetailsService userDetailsService;

    public AuthServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, UserDetailsService userDetailsService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        //this.authenticationManager = authenticationManager;
        //this.jwtUtils = jwtUtils;
        this.userDetailsService = userDetailsService;
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
    public String loginUser(LoginDTO loginDTO) {

        String email = loginDTO.getUsername();
        String loginPassword = loginDTO.getPassword();

        UserDetails user = userDetailsService.loadUserByUsername(email);

        String dbPassword = user.getPassword();

        if (passwordEncoder.matches(loginPassword, dbPassword)) {
            Authentication authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());

            SecurityContextHolder.getContext().setAuthentication(authentication);

            return "Login successful";
        }
        else {
            return "Invalid credentials";
        }
    }
}
