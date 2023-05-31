package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.client.ExchangeRatesClient;
import org.example.client.dto.ExchangeRatesResponse;
import org.example.exceptions.NoSuchOrderException;
import org.example.service.OrderService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.Map;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class OrderController {

    private final OrderService service;

    //all works correct
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/cart")
    public String getAllOrders(Principal principal, Model model){
        Map<String, Object> map = service.getAllOrders(principal);
        if(map!=null) {
            model.addAttribute("orders", map.get("orders"));
            model.addAttribute("total", map.get("total"));
            model.addAttribute("usd", map.get("usd"));
            model.addAttribute("eur", map.get("eur"));
            model.addAttribute("count", map.get("count"));
            return "carts";
        }
        return "error";
    }

    //all works correct
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/cart")
    public String endOrder(Principal principal){
        if(service.endOrder(principal)){
            return "redirect:/profile";
        }else{
            return "redirect:/cart?error";
        }
    }

    //all works correct
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/products/{id}/addToCart")
    public String addToCart(@PathVariable("id")UUID id,
                            Principal principal){
        service.addNewOrder(principal, id);
        return "redirect:/products/{id}";
    }

    //all works correct
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/cart/{id}")
    public String deleteOrder(@PathVariable("id") UUID id) throws NoSuchOrderException {
        service.deleteOrder(id);
        return "redirect:/cart";
    }

    //all works correct
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/cart/deleteAll")
    public String deleteAllOrder(Principal principal){
        service.deleteAll(principal);
        return "redirect:/cart";
    }
}
