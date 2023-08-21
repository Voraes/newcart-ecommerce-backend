package com.voraes.newcartbackend.entity;

import com.voraes.newcartbackend.enums.OrderStatus;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<CartItem> cartItems = new ArrayList<>();

    @Column(name = "order_date")
    @NotNull
    private LocalDateTime orderDate;

    @Column(name = "total_amount")
    @NotNull
    private double totalAmount;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    @NotNull
    private OrderStatus status;
}
