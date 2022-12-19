package com.ecommerce.orders.repos;

import com.ecommerce.orders.models.OrderModel;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderRepo extends MongoRepository<OrderModel, String> {
}
