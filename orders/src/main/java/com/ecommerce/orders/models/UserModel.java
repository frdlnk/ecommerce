package com.ecommerce.orders.models;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "users")

public class UserModel {
    @Getter @Setter
    private String _id;

    @Getter @Setter
    private String email;

    @Getter @Setter
    private String password;

    @Getter @Setter
    private String name;

    @Getter @Setter
    private List cart;

    @Getter @Setter
    private List notifications;
}
