package org.example.repository;

import org.example.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {
    Product findProductByName(String name);
}
