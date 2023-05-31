package org.example.controller;

import jdk.jfr.ContentType;
import lombok.RequiredArgsConstructor;
import org.example.entity.MediaComments;
import org.example.entity.MediaContent;
import org.example.exceptions.NoSuchContentException;
import org.example.service.MediaCommentsService;
import org.example.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.security.Principal;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class MediaCommentsController {

    private final MediaCommentsService service;

    //all works correct
//    @ApiOperation(value = {
//            @ApiResponse(@ContentType)
//    })
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/media/{id}/addComment")
    public String addComment(@PathVariable("id")UUID id,
                             @Valid @RequestParam("comment") String comment,
                             Principal principal) throws NoSuchContentException {
        service.save(principal, id, comment);
        return "redirect:/media/{id}";
    }

    //all works correct
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/media/{id}/deleteComment")
    public String deleteComment(@PathVariable("id") UUID id,
                                @RequestParam("commentId") UUID idComment){
        if(!service.delete(id, idComment)){
            return "error";
        }
        return "redirect:/media/{id}";
    }

}
