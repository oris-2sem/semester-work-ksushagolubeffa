package org.example.controller.rest;

import lombok.RequiredArgsConstructor;
import org.example.exceptions.NoSuchContentException;
import org.example.service.LikeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class LikeRestController {
//
//    private final LikeService service;
//
//    @PostMapping("/media/{id}/removeLike")
//    public ResponseEntity removeLike(@PathVariable("id") UUID id, Principal principal) throws NoSuchContentException {
//        service.removeLike(principal, id);
//        return ResponseEntity.ok().build();
//    }
//
//    @PostMapping("/media/{id}/addLike")
//    public ResponseEntity addLike(@PathVariable("id") UUID id, Principal principal) throws NoSuchContentException {
//        service.addLike(principal, id);
//        return ResponseEntity.ok().build();
//    }
}
