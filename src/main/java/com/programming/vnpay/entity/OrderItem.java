package com.programming.vnpay.entity;

import ch.qos.logback.core.model.INamedModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Table
@Entity(name = "orderItem")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItem implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer quantity;
    private Double price;

    @ManyToOne // Khai báo mối quan hệ nhiều-1 với lớp Orders
    private Orders order;

    @ManyToOne // Khai báo mối quan hệ nhiều-1 với lớp Product
    private Product product;


}
