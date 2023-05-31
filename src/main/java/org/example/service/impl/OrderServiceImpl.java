package org.example.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.client.ExchangeRatesClient;
import org.example.client.dto.ExchangeRatesResponse;
import org.example.entity.Order;
import org.example.entity.Product;
import org.example.entity.User;
import org.example.exceptions.NoSuchOrderException;
import org.example.exceptions.NoSuchProductException;
import org.example.repository.OrderRepository;
import org.example.repository.ProductRepository;
import org.example.repository.UserRepository;
import org.example.service.OrderService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.security.Principal;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository repository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    @Override
    public Map<String, Object> getAllOrders(Principal principal) {
        User user = getUserByPrincipal(principal);
        List<Order> orderList = new ArrayList<>();
        repository.findAll().forEach(order -> {
            if(order.getUser().getUuid().equals(user.getUuid()) && !order.isExecuted()){
                orderList.add(order);
            }
        });
        if(orderList!=null){
            List<Product> products = findProductsById(orderList);
            int sum = products.stream().mapToInt(Product::getPrice).sum();
            int usd = products.stream().mapToInt(Product::getUsdPrice).sum();
            int eur = products.stream().mapToInt(Product::getEurPrice).sum();
            Map <String, Object> map = new HashMap<>();
            boolean count = orderList.size()!=0;
            map.put("orders", orderList);
            map.put("total", sum);
            map.put("count", count);
            map.put("usd", usd);
            map.put("eur", eur);
            return map;
        }
        return null;
    }

    @Override
    public Order findById(UUID id) throws NoSuchOrderException {
        return repository.findById(id).orElseThrow(() -> new NoSuchOrderException(id));
    }

    @Override
    public void endOrders(List<Order> orders) {
        User user = orders.get(0).getUser();
        List<Product> products = findProductsById(orders);
        int sum = products.stream().mapToInt(Product::getPrice).sum();
        int money = user.getBalance();
        if(money>=sum) {
            userRepository.setBalance(money - sum, user.getUuid());
            orders.forEach(order -> {
                order.setExecuted(true);
            });
        }
    }

    @Override
    public List<Order> getAll() {
        return repository.findAll();
    }

    @Transactional
    @Override
    public void deleteAll(Principal principal) {
        User user = getUserByPrincipal(principal);
        repository.deleteAllByUser(user);
    }

    @Override
    public User getUserByPrincipal(Principal principal) {
        if (principal == null) return new User();
        return userRepository.findUserByEmail(principal.getName());
    }

    @Override
    public List<Product> findProductsById(List<Order> orders){
        if (orders!=null) {
            return orders.stream()
                    .map(order -> productRepository.findById(order.getProduct().getUuid()))
                    .flatMap(Optional::stream)
                    .collect(Collectors.toList());
        }
        return null;
    }

    @Transactional
    @Override
    public boolean endOrder(Principal principal) {
        User user = getUserByPrincipal(principal);
        List<Order> orders = new ArrayList<>();
        repository.findAll().forEach(order -> {
            if(order.getUser().getUuid().equals(user.getUuid()) && !order.isExecuted()){
                orders.add(order);
            }
        });
        int money = user.getBalance();
        List<Product> products = findProductsById(orders);
        int sum = products.stream().mapToInt(Product::getPrice).sum();
        if(money>=sum) {
            userRepository.setBalance(money - sum, user.getUuid());
            orders.forEach(order -> {
                order.setExecuted(true);
                repository.save(order);
            });
            return true;
        }
        return false;
    }

    @Override
    public void addNewOrder(Principal principal, UUID id) {
        User user = getUserByPrincipal(principal);
        Order order =  new Order(user.getUuid(), id, false);
        user.getOrders().add(order);
        repository.save(order);
    }

    @Override
    public boolean deleteOrder(UUID id) throws NoSuchOrderException {
        Order order = repository.findById(id).orElseThrow(() -> new NoSuchOrderException(id));
        if(order!=null){
            repository.delete(order);
            return true;
        } else{
            log.error("not such order in database");
            return false;
        }
    }
}
