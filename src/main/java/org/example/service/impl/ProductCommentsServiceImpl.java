package org.example.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.entity.Product;
import org.example.entity.ProductComments;
import org.example.entity.User;
import org.example.exceptions.NoSuchProductException;
import org.example.repository.ProductCommentsRepository;
import org.example.repository.ProductRepository;
import org.example.repository.UserRepository;
import org.example.service.ProductCommentsService;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductCommentsServiceImpl implements ProductCommentsService {

    private final ProductCommentsRepository repository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    @Override
    public void saveComment(Principal principal, UUID id, String comment) throws NoSuchProductException {
        ProductComments comments = ProductComments.builder()
                                .product(productRepository.findById(id).orElseThrow(() -> new NoSuchProductException(id)))
                                .text(comment)
                                .username(getUserByPrincipal(principal).getUsername())
                                .build();
        repository.save(comments);
    }

    @Override
    public boolean delete(UUID idProduct, UUID id) {
        ProductComments comment = repository.findById(id).orElse(null);
        if(comment!=null){
            if(comment.getProduct().getUuid().equals(idProduct)){
                repository.delete(comment);
                return true;
            }
            log.error("Comment with id = {} is not found", id);
        } else{
            log.error("not such comment in database");
        }
        return false;
    }

    @Override
    public User getUserByPrincipal(Principal principal) {
        if (principal == null) return new User();
        return userRepository.findUserByEmail(principal.getName());
    }
}
