package com.ecommerce.auth.repos;

import com.ecommerce.auth.modelos.UserModel;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepo extends MongoRepository<UserModel, String> {
    UserModel findByEmail(String email);
}
