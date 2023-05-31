package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.entity.Product;
import org.example.entity.ProductComments;
import org.example.entity.User;
import org.example.exceptions.NoSuchProductException;
import org.example.service.ProductCommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class ProductCommentsController {

    @Autowired
    private final ProductCommentsService service;

    //all works correct
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/products/{id}/addComment")
    public String addComment(@PathVariable("id") UUID id,
                             @Valid @RequestParam String comment,
                             Principal principal) throws NoSuchProductException {
        service.saveComment(principal, id, comment);
        return "redirect:/products/{id}";
    }

    //all works correct
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/products/{id}/deleteComment")
    public String deleteComment(@PathVariable("id") UUID id,
                                 @RequestParam ("commentId") UUID idComment){
        if(!service.delete(id, idComment)){
            return "error";
        }
        return "redirect:/products/{id}";
    }
}
