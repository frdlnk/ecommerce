package com.ecommerce.cart.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "products")
public class ProductModel {
    @Getter @Setter
    private String _id;
    @Getter @Setter
    private String title;
    @Getter @Setter
    private String description;
    @Getter @Setter
    private Double price;
    @Getter @Setter
    private List<String> categories;

}
