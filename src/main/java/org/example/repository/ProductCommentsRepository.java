package org.example.repository;

import org.example.entity.ProductComments;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProductCommentsRepository extends JpaRepository<ProductComments, UUID> {
}
