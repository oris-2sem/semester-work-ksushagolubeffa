package org.example.controller.rest;

import org.example.entity.Product;
import org.example.entity.response.ProductResponse;
import org.example.exceptions.NoSuchProductException;
import org.example.service.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
public class ProductRestController {
    private final ProductService productService;

    public ProductRestController(@Qualifier("productServiceBase") ProductService productService) {
        this.productService = productService;
    }

    //all works correct
    @GetMapping("/products/rest")
    @ResponseBody
    public List<ProductResponse> getAllProducts(){
        List<Product> productList = productService.getAllProducts();
        List<ProductResponse> products = new ArrayList<>();
        productList.forEach(product -> {
            products.add(new ProductResponse(
                    product.getUuid(), product.getName(), product.getDescription(), product.getPrice()
            ));
        });
        return products;
    }

    //all works correct
    @GetMapping("/products/{id}/image")
    public ResponseEntity<?> showImage(@PathVariable("id") UUID id) throws NoSuchProductException {
        Product product = productService.findProductById(id);
        return ResponseEntity.ok()
                .header("fileName", product.getName())
                .contentType(MediaType.valueOf(product.getImage().getContentType()))
                .contentLength(product.getImage().getSize())
                .body(new InputStreamResource(new ByteArrayInputStream(product.getImage().getBytes())));
    }

}
