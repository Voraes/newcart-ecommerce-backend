package com.voraes.newcartbackend.service;

import com.voraes.newcartbackend.DTO.LoginDTO;
import com.voraes.newcartbackend.DTO.UserDTO;

public interface AuthService {

    String loginUser(LoginDTO userDTO);

    void registerUser(UserDTO userDTO);
}
