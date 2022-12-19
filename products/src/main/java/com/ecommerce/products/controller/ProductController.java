package com.ecommerce.products.controller;

import com.ecommerce.products.models.ProductModel;
import com.ecommerce.products.repo.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    ProductRepository pRepo;

    @GetMapping("/health")
    public ResponseEntity<String> home() {
        return ResponseEntity.status(HttpStatus.OK).body("Operational");
    }

    @GetMapping("/seed")
    public String seed(){
        for (int i = 0; i < 50; i++) {
            List<String> categories = new ArrayList<>();
            categories.add("Category");
            ProductModel product = new ProductModel();
            product.setTitle("Product " + i);
            product.setDescription("Product number " + i);
            product.setPrice(930.00);
            product.setCategories(categories);
            pRepo.save(product);
        }
        return "Product seeded to db";
    }

    @GetMapping("/all")
    public ResponseEntity getProductsPaginated(@RequestParam int page, int limit){
        int startIndex = page -1;
        startIndex *= limit;
        int endIndex = page * limit;
        List<ProductModel> products = pRepo.findAll();
        List<ProductModel> paginatedProducts = products.stream().skip(startIndex).limit(endIndex).collect(Collectors.toList());
        if(paginatedProducts.isEmpty()) return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No more products to get");
        return ResponseEntity.status(HttpStatus.OK).body(paginatedProducts);
    }

    @GetMapping("/")
    public ResponseEntity getProductById (@RequestParam String id) {
        Optional<ProductModel> product = pRepo.findById(id);
        if(product.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("This product was not found");
        return ResponseEntity.status(HttpStatus.OK).body(product);
    }
}
