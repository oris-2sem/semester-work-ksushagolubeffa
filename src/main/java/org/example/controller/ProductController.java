package org.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.entity.Product;
import org.example.entity.User;
import org.example.entity.response.ProductResponse;
import org.example.exceptions.NoSuchProductException;
import org.example.service.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
@Slf4j
public class ProductController {

    private final ProductService productService;

    public ProductController(@Qualifier("productServiceBase") ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products")
    public String getAllProducts(Model model, Principal principal){
        User user = productService.getUserByPrincipal(principal);
        List<Product> productList = productService.getAllProducts();
        List<ProductResponse> products = new ArrayList<>();
        productList.forEach(product -> {
            products.add(new ProductResponse(
                    product.getUuid(), product.getName(), product.getDescription(), product.getPrice()
            ));
        });
        model.addAttribute("products", productList);
        model.addAttribute("isAdmin", user.getRole()== User.Role.ADMIN);
        return "products";
    }


    //all works correct
    @GetMapping("/products/{id}")
    public String getProduct(@PathVariable("id") UUID id,
                             Model model,
                             Principal principal) throws NoSuchProductException {
        User user = productService.getUserByPrincipal(principal);
        model.addAttribute("currentUser", user);
        model.addAttribute("comments", productService.getAllComments(id));
        model.addAttribute("count", productService.getAllComments(id).size() > 0);
        model.addAttribute("product", productService.findProductById(id));
        model.addAttribute("isAdmin", user.getRole()== User.Role.ADMIN);
        model.addAttribute("isAuth", user.getRole() == User.Role.USER);
        return "product-information";
    }

    //all works correct
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/products/add")
    public String addProduct(@Valid @RequestParam ("name") String name,
                             @Valid @RequestParam ("description") String description,
                             @Valid @RequestParam ("price") int price,
                             @Valid @RequestParam ("image") MultipartFile imageFile) throws IOException {
        productService.addNewProduct(name, description, price, imageFile);
        return "redirect:/products";
    }

    //all works correct
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/products/add")
    public String addProductPage(){
        return "product-add";
    }

//    all works correct
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/products/edit/{id}")
    public String updateProduct(@PathVariable ("id") UUID id,
                                @Valid @RequestParam ("name") String name,
                                @Valid @RequestParam ("description") String description,
                                @Valid @RequestParam ("price") int price,
                                @Valid @RequestParam ("image") MultipartFile imageFile) throws IOException, NoSuchProductException {
        productService.updateProduct(id, name, description, price, imageFile);
        return "redirect:/products";
    }

//    all works correct
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/products/edit/{id}")
    public String editProduct(@PathVariable UUID id,
                              Model model) throws NoSuchProductException {
        model.addAttribute("product", productService.findProductById(id));
        return "product-edit";
    }

//    all works correct
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/products/{id}/delete")
    public String deleteProduct(@PathVariable UUID id) throws NoSuchProductException {
        productService.deleteProduct(id);
        return "redirect:/products";
    }
}
