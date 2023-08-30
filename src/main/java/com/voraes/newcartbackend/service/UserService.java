package com.voraes.newcartbackend.service;

import com.voraes.newcartbackend.DTO.UserDTO;
import com.voraes.newcartbackend.entity.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();

    User getUserById(Long id);

    UserDetails findByUsername(String username);


}
