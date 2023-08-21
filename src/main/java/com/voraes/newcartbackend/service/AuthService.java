package com.voraes.newcartbackend.service;

import com.voraes.newcartbackend.DTO.UserDTO;

public interface AuthService {

    void loginUser(UserDTO userDTO);

    void registerUser(UserDTO userDTO);
}
