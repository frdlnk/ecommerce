package com.ecommerce.products.repo;

import com.ecommerce.products.models.ProductModel;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<ProductModel, String> {
}
