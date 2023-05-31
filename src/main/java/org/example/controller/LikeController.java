package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.entity.MediaContent;
import org.example.exceptions.NoSuchContentException;
import org.example.service.LikeService;
import org.example.service.MediaContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.annotation.security.PermitAll;
import java.security.Principal;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class LikeController {

    @Autowired
    private final LikeService service;

    //all works correct
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/media/{id}/removeLike")
    public String deleteLike(@PathVariable("id") UUID id, Principal principal) throws NoSuchContentException {
        service.removeLike(principal, id);
        return "redirect:/media/{id}";
    }

    //all works correct
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/media/{id}/addLike")
    public String addLike(@PathVariable("id") UUID id,
                           Principal principal) throws NoSuchContentException {
        service.addLike(principal, id);
        return "redirect:/media/{id}";
    }
}
