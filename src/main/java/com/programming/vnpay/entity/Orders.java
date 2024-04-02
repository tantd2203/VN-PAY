package com.programming.vnpay.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Table
@Entity(name = "orders")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Orders implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nameCustomer;
    private int amount_paid;
    private String address;
    @CreatedDate
    private Date    createDate;


    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems;


}
