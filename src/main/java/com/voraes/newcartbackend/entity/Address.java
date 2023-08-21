package com.voraes.newcartbackend.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Data
@Table(name = "addresses")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "name", unique = true)
    @NotEmpty(message = "Name cannot be empty")
    @Size(min = 3, max = 30, message = "Name must be between 3 and 30 characters")
    private String name;

    @Column(name = "street_address")
    @NotEmpty(message = "Street address cannot be empty")
    @Size(min = 3, max = 50, message = "Street address must be between 3 and 50 characters")
    private String streetAddress;

    @Column(name = "city")
    @NotEmpty(message = "City cannot be empty")
    @Size(min = 3, max = 20, message = "City must be between 3 and 20 characters")
    private String city;

    @Column(name = "state")
    @NotEmpty(message = "State cannot be empty")
    @Size(min = 3, max = 20, message = "State must be between 3 and 20 characters")
    private String state;

    @Column(name = "country")
    @NotEmpty(message = "Country cannot be empty")
    @Size(min = 3, max = 20, message = "Country must be between 3 and 20 characters")
    private String country;

    @Column(name = "zip_code")
    @NotEmpty(message = "Zip code cannot be empty")
    private String zipCode;
    
}
