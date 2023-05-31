package org.example.service;

import org.example.entity.Product;
import org.example.entity.ProductComments;
import org.example.entity.User;
import org.example.exceptions.NoSuchProductException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.List;
import java.util.UUID;

public interface ProductService {

    List<Product> getAllProducts();

//    MultipartFile getImage(UUID id);


    List<ProductComments> getAllComments(UUID id);

    void updateProduct(UUID id, String name, String description, int price, MultipartFile imageFile) throws IOException, NoSuchProductException;

    Product findProductById(UUID id) throws NoSuchProductException;

    void addNewProduct(String name, String description, int price, MultipartFile imageFile) throws IOException;

    void deleteProduct(UUID id) throws NoSuchProductException;

    User getUserByPrincipal(Principal principal);
}
