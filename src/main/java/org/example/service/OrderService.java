package org.example.service;

import org.example.entity.Order;
import org.example.entity.Product;
import org.example.entity.User;
import org.example.exceptions.NoSuchOrderException;

import java.security.Principal;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public interface OrderService {

    Map<String, Object> getAllOrders(Principal principal);

    Order findById(UUID id) throws NoSuchOrderException;

    void endOrders(List<Order> orders);

    List<Order> getAll();

    void deleteAll(Principal principal);

    List<Product> findProductsById(List<Order> orders);

    User getUserByPrincipal(Principal principal);

    boolean endOrder(Principal principal);

    void addNewOrder(Principal principal, UUID id);

    boolean deleteOrder(UUID id) throws NoSuchOrderException;
}
