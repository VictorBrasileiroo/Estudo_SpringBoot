package com.braza.ecommerce_fkmodas.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.text.DecimalFormat;

@Data
@Entity
@Table(name = "products")
public class ProductModel {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String description;

    @Column(nullable = false)
    private float price;
}
