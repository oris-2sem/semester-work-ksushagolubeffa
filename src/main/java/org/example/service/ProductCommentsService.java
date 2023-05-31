package org.example.service;

import org.example.entity.Product;
import org.example.entity.ProductComments;
import org.example.entity.User;
import org.example.exceptions.NoSuchProductException;

import java.security.Principal;
import java.util.List;
import java.util.UUID;

public interface ProductCommentsService {

    void saveComment(Principal principal, UUID id, String comment) throws NoSuchProductException;

    boolean delete(UUID idProduct, UUID idComment);

    User getUserByPrincipal(Principal principal);

}
