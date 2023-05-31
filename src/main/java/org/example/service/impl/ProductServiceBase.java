package org.example.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.example.client.ExchangeRatesClient;
import org.example.client.dto.ExchangeRatesResponse;
import org.example.entity.Image;
import org.example.entity.Product;
import org.example.entity.ProductComments;
import org.example.entity.User;
import org.example.exceptions.NoSuchProductException;
import org.example.repository.ImageRepository;
import org.example.repository.ProductCommentsRepository;
import org.example.repository.ProductRepository;
import org.example.repository.UserRepository;
import org.example.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.security.Principal;
import java.util.*;

@Slf4j
@Service
public class ProductServiceBase implements ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductCommentsRepository commentsRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ImageRepository imageRepository;
    @Autowired
    private ExchangeRatesClient exchangeRatesClient;

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public List<ProductComments> getAllComments(UUID id) {
        List<ProductComments> commentList = new ArrayList<>();
        commentsRepository.findAll().forEach(comments -> {
            if (comments.getProduct().getUuid().equals(id)) {
                commentList.add(comments);
            }
        });
        return commentList;
    }

    @Override
    public void updateProduct(UUID id, String name, String description, int price, MultipartFile imageFile) throws IOException, NoSuchProductException {
        Product product = productRepository.findById(id).orElseThrow(() -> new NoSuchProductException(id));
        if (imageFile.getSize() != 0) {
            imageRepository.delete(product.getImage());
            Image image = toImageEntity(imageFile);
            imageRepository.save(image);
            product.setImage(image);
            productRepository.save(product);
            image.setProduct(product);
            imageRepository.save(image);
        }
        product.setName(name);
        product.setDescription(description);
        product.setPrice(price);
        productRepository.save(product);
    }

    @Override
    public Product findProductById(UUID id) throws NoSuchProductException {
        return productRepository.findById(id).orElseThrow(() -> new NoSuchProductException(id));
    }

    @Override
    public void addNewProduct(String name, String description, int price, MultipartFile imageFile) throws IOException {
        Map<String, Double> map = getRate();
        Product product = Product.builder()
                .name(name)
                .description(description)
                .price(price)
                .usdPrice((int) (map.get("usd") * price))
                .eurPrice((int) (map.get("eur") * price))
                .build();
        System.out.println((int) (map.get("eur") * price));
        System.out.println(map.get("eur"));
        System.out.println((int) (map.get("usd") * price));
        System.out.println(map.get("usd"));
        if (imageFile.getSize() != 0) {
            Image image = toImageEntity(imageFile);
            imageRepository.save(image);
            product.setImage(image);
            productRepository.save(product);
            image.setProduct(product);
            imageRepository.save(image);
        }
        productRepository.save(product);
    }

    @Override
    public void deleteProduct(UUID id) throws NoSuchProductException {
        Product product = productRepository.findById(id).orElseThrow(() -> new NoSuchProductException(id));
        imageRepository.delete(product.getImage());
        productRepository.deleteById(id);

    }

    @Override
    public User getUserByPrincipal(Principal principal) {
        if (principal == null) return new User();
        return userRepository.findUserByEmail(principal.getName());
    }

    private Image toImageEntity(MultipartFile file) throws IOException {
        Image image = new Image();
        image.setContentType(file.getContentType());
        image.setSize(file.getSize());
        image.setBytes(file.getBytes());
        return image;
    }

    private Map<String, Double> getRate() {
        ExchangeRatesResponse response = exchangeRatesClient.getExchangeRates();
        Map<String, Double> mapRates = response.getRates();
        Double usd = mapRates.get("USD");
        Double eur = mapRates.get("EUR");
        Map<String, Double> result = new HashMap<>();
        result.put("usd", usd);
        result.put("eur", eur);
        return result;
    }

}
