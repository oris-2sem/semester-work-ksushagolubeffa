package org.example.repository;

import org.example.entity.Order;
import org.example.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface OrderRepository extends JpaRepository<Order, UUID> {

    void deleteAllByUser(User user);

}
