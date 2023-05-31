package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.entity.MediaContent;
import org.example.entity.Product;
import org.example.entity.User;
import org.example.entity.response.MediaContentResponse;
import org.example.entity.response.UserResponse;
import org.example.exceptions.UserNotFoundException;
import org.example.security.detail.UserDetailsImpl;
import org.example.service.ProductService;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.Banner;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.security.PermitAll;
import javax.transaction.Transactional;
import javax.validation.Valid;

import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Controller
public class UsersController {

    private final UserService service;

    public UsersController(@Qualifier("userServiceBase") UserService userService) {
        this.service = userService;
    }

    //all works correct
    @PermitAll
    @GetMapping("/users/{id}")
    public String userInfo(@PathVariable("id") UUID id,
                           Model model) throws UserNotFoundException {
        model.addAttribute("user", service.findUserById(id));
        return "user";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/profile/media")
    public String getMyMedia(Principal principal, Model model) throws UserNotFoundException {
        User user = service.findUserByEmail(principal);
        List<MediaContent> contentList = user.getContentList();
        List<MediaContentResponse> list = new ArrayList<>();
        contentList.forEach(content -> {
            list.add(new MediaContentResponse(
                    content.getUuid(),
                    content.getName(),
                    content.getDescription(),
                    content.getStatus()
            ));
        });
        model.addAttribute("mediaList", contentList);
        return "media";
    }

    @PermitAll
    @GetMapping("/users/{id}/media")
    public String allMedia(@PathVariable("id") UUID id, Model model) throws UserNotFoundException {
        User user = service.findUserById(id);
        List<MediaContent> contentList = user.getContentList().stream().filter(content -> content.getStatus() == MediaContent.Status.ACCEPTED).toList();
        List<MediaContentResponse> list = new ArrayList<>();
        contentList.forEach(content -> {
            list.add(new MediaContentResponse(
                    content.getUuid(),
                    content.getName(),
                    content.getDescription()
            ));
        });
        model.addAttribute("mediaList", contentList);
        return "media";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/users")
    public String getAllUsers(Model model) {
        List<User> userList = service.getAllUsers();
        List<UserResponse> users = new ArrayList<>();

        userList.forEach(user->
                users.add(new UserResponse(user.getUuid(),
                        user.getUsername(),
                        user.getEmail(),
                        user.getRole(),
                        user.getBalance(),
                        user.getState()))
        );
        model.addAttribute("users", userList);
        return "all-users";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/profile/likes")
    public String getAllLikes(Principal principal, Model model){
        List<MediaContent> contentList = service.findAllLikes(principal);
        List<MediaContentResponse> list = new ArrayList<>();
        contentList.forEach(content -> {
            list.add(new MediaContentResponse(
                    content.getUuid(),
                    content.getName(),
                    content.getDescription()
            ));
        });
        model.addAttribute("mediaList", contentList);
        return "media";
    }

    // all works correct
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/profile")
    public String getProfilePage(Principal principal,
                                 Model model) throws UserNotFoundException {
        User user = service.findUserByEmail(principal);
        if(user == null){
            return "home-page";
        }
        model.addAttribute("user", user);
        model.addAttribute("isAdmin", user.getRole()== User.Role.ADMIN);
        return "profile";
    }

    //all works correct
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/profile/edit")
    public String updateProfile(@Valid @RequestParam ("username") String username,
                                @Valid @RequestParam ("password") String password,
                                @Valid @RequestParam ("newPassword") String newPassword,
                                @Valid @RequestParam ("image") MultipartFile image,
                                Principal principal,
                                Model model) throws IOException, UserNotFoundException {
        if(service.arePasswordsEquals(principal, password)){
            service.updateUser(principal, username, newPassword, image);
        }
        else{
            return "redirect:/error";
        }
        model.addAttribute("user", service.findUserByEmail(principal));
        model.addAttribute("isAdmin", service.findUserByEmail(principal).getRole()== User.Role.ADMIN);
        return "profile";
    }

    // all works correct
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/profile/edit")
    public String editProfile(Principal principal, Model model) throws UserNotFoundException {
        User user = service.findUserByEmail(principal);
        if(user==null){
            return "redirect:/";
        }
        model.addAttribute("user", user);
        return "profile-edit";
    }

    // all works correct
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/profile")
    public String deleteUser(Principal principal) throws UserNotFoundException {
        service.deleteUser(principal);
        SecurityContextHolder.clearContext();
        return "redirect:/signUp";
    }

    //all works correct
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/logout")
    public String exitUser() {
        return "redirect:/";
    }

    //all works correct
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/profile/orders")
    public String getOrders(Principal principal, Model model){
        List<Product> list = service.findAllOrders(principal);
        if(list!=null) {
            model.addAttribute("products", list);
            return "orders";
        }
        return "error";
    }

    //all works correct
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/profile/check")
    public String getContentPage(Model model){
        model.addAttribute("contentList", service.getContentForCheck());
        return "check";
    }

}
