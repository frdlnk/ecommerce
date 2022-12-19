package com.ecommerce.cart.repos;

import com.ecommerce.cart.models.UserModel;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<UserModel, String> {
}
