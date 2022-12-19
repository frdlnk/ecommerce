package com.ecommerce.search.repos;
import com.ecommerce.search.models.ProductModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface ProductRepository extends MongoRepository<ProductModel, String> {

    @Query("{'title': {$regex: ?0, $options: 'i' }}")
    List<ProductModel> search(String s);
}

