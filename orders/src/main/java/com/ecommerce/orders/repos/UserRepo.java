package com.ecommerce.orders.repos;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepo extends MongoRepository<UserRepo, String> {
}
