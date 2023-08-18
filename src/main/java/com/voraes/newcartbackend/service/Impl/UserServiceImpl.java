package com.voraes.newcartbackend.service.Impl;

import com.voraes.newcartbackend.DTO.UserDTO;
import com.voraes.newcartbackend.entity.Address;
import com.voraes.newcartbackend.entity.Order;
import com.voraes.newcartbackend.entity.User;
import com.voraes.newcartbackend.repository.UserRepository;
import com.voraes.newcartbackend.service.UserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    @Override
    public void registerUser(UserDTO userDTO) {

        List<Address> addresses = new ArrayList<>();
        List<Order> orders = new ArrayList<>();

        User newUser = new User();
        newUser.setUsername(userDTO.getUsername());
        newUser.setPassword(userDTO.getPassword());
        newUser.setEmail(userDTO.getEmail());
        newUser.setRole("USER");
        newUser.setAddresses(addresses);
        newUser.setOrders(orders);

        userRepository.save(newUser);
    }
}
