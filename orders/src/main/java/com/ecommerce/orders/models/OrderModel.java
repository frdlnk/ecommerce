package com.ecommerce.orders.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "orders")
public class OrderModel {
    @Getter @Setter
    private String _id;

    @Getter @Setter
    private String email;

    @Getter @Setter
    private String name;

    @Getter @Setter
    private List products;

    @Getter @Setter
    private Double total;
}
