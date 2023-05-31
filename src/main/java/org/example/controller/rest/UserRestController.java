package org.example.controller.rest;


import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.example.entity.MediaContent;
import org.example.entity.Product;
import org.example.entity.User;
import org.example.entity.response.MediaContentResponse;
import org.example.entity.response.UserResponse;
import org.example.exceptions.UserNotFoundException;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.PermitAll;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.security.Principal;
import java.util.*;

@Tag(name = "User", description = "The User API")
@RestController
public class UserRestController {

    private final UserService service;


    public UserRestController(@Qualifier("userServiceBase") UserService userService) {
        this.service = userService;
    }

//    @PreAuthorize("hasAuthority('ADMIN')")
//    @RequestMapping(value = "/users", produces = "application/json")
//    public List<UserResponse> getAllUsers() {
//        List<User> userList = service.getAllUsers();
//        List<UserResponse> users = new ArrayList<>();
//
//        userList.forEach(user ->
//                users.add(new UserResponse(user.getUuid(),
//                        user.getUsername(),
//                        user.getEmail(),
//                        user.getRole(),
//                        user.getBalance(),
//                        user.getState()))
//        );
//        return users;
//    }

    //all works correct
//    @PreAuthorize("isAuthenticated()")
//    @GetMapping("/profile/media")
//    public List<MediaContentResponse> getMyMedia(Principal principal) throws UserNotFoundException {
//        User user = service.findUserByEmail(principal);
//        List<MediaContent> contentList = user.getContentList();
//        List<MediaContentResponse> list = new ArrayList<>();
//        contentList.forEach(content -> {
//            list.add(new MediaContentResponse(
//                    content.getUuid(),
//                    content.getName(),
//                    content.getDescription(),
//                    content.getStatus()
//            ));
//        });
//        return list;
//    }

//    @Operation(summary = "Gets all user`s media", tags = "user")
//    @ApiResponses(value = {
//            @ApiResponse(
//                    responseCode = "200",
//                    description = "Found media",
//                    content = {
//                            @Content(
//                                    mediaType = "application/json",
//                                    array = @ArraySchema(schema = @Schema(implementation =  MediaContentResponse.class)))
//                    })
//    })
    //all works correct
//    @PermitAll
//    @GetMapping("/users/{id}/media")
//    public List <MediaContentResponse> allMedia(@PathVariable("id") UUID id) throws UserNotFoundException {
//        User user = service.findUserById(id);
//        List<MediaContent> contentList = user.getContentList().stream().filter(content -> content.getStatus() == MediaContent.Status.ACCEPTED).toList();
//        List<MediaContentResponse> list = new ArrayList<>();
//        contentList.forEach(content -> {
//            list.add(new MediaContentResponse(
//                    content.getUuid(),
//                    content.getName(),
//                    content.getDescription()
//            ));
//        });
//        return list;
//    }

//    @Operation(summary = "Gets all user`s liked media", tags = "user")
//    @ApiResponses(value = {
//            @ApiResponse(
//                    responseCode = "200",
//                    description = "Found media",
//                    content = {
//                            @Content(
//                                    mediaType = "application/json",
//                                    array = @ArraySchema(schema = @Schema(implementation =  MediaContentResponse.class)))
//                    })
//    })
    //all works correct
//    @PreAuthorize("isAuthenticated()")
//    @GetMapping("/profile/likes")
//    public List<MediaContentResponse> getAllLikes(Principal principal){
//        List<MediaContent> contentList = service.findAllLikes(principal);
//        List<MediaContentResponse> list = new ArrayList<>();
//        contentList.forEach(content -> {
//            list.add(new MediaContentResponse(
//                    content.getUuid(),
//                    content.getName(),
//                    content.getDescription()
//            ));
//        });
//        return list;
//    }

    @GetMapping("/users/{id}/image")
    public ResponseEntity<?> showImage(@PathVariable("id") UUID id) throws IOException, UserNotFoundException {
        User user = service.findUserById(id);
        return ResponseEntity.ok()
                .header("fileName", user.getUsername())
                .contentType(MediaType.valueOf(user.getImage().getContentType()))
                .contentLength(user.getImage().getSize())
                .body(new InputStreamResource(new ByteArrayInputStream(user.getImage().getBytes())));
    }
}
