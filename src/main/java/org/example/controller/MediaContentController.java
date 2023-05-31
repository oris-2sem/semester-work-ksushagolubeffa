package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.entity.MediaContent;
import org.example.entity.User;
import org.example.entity.response.MediaContentResponse;
import org.example.exceptions.NoSuchContentException;
import org.example.service.MediaContentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.security.PermitAll;
import javax.validation.Valid;
import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class MediaContentController {
    private final MediaContentService service;


    @PermitAll
    @GetMapping("/media")
    public String getAllMediaContents(Model model){
        List<MediaContent> contentList = service.findAllContent();
        List<MediaContentResponse> list = new ArrayList<>();
        contentList.forEach(content -> {
            list.add(new MediaContentResponse(
                    content.getUuid(), content.getName(), content.getDescription(), content.getUser().getUsername()
            ));
        });
        model.addAttribute("mediaList",contentList);
        return "media";
    }

    //all works good
    @PermitAll
    @GetMapping("/media/{content}")
    public ModelAndView mediaInfo(@PathVariable MediaContent content, Model model, Principal principal) {
        User user = service.getUserByPrincipal(principal);
        model.addAttribute("isLiked", service.isLiked(content, principal));
        model.addAttribute("currentUser", user);
        model.addAttribute("isAuth", user.getRole() == User.Role.USER);
        model.addAttribute("count", service.findAllComments(content).size() > 0);
        model.addAttribute("isAuthor", content.getUser() == user);
        model.addAttribute("isAdmin", user.getRole() == User.Role.ADMIN);
        model.addAttribute("comments", service.findAllComments(content));
        model.addAttribute("content", content);
        model.addAttribute("likes", service.getAllLikes(content));
        return new ModelAndView("media-info", model.asMap());
    }

    @PermitAll
    @GetMapping("/")
    public String openHomePage(Model model) {
        model.addAttribute("contents", service.findMostLiked());
        return "home-page";
    }

    @PermitAll
    @GetMapping("/media/search")
    public String findMediaByQuery(@RequestParam String search, Model model) {
        model.addAttribute("listMedia", service.searchMediaContent(search));
        return "media-page";
    }

    //all works correct
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/media/add")
    public String sendToCheck(Principal principal,
                              @Valid @RequestParam("name") String name,
                              @Valid @RequestParam("description") String description,
                              @Valid @RequestParam("video") MultipartFile video,
                              @Valid @RequestParam("previewImage") MultipartFile image) throws IOException {
        service.addMediaContent(principal, name, description, image, video);
        return "redirect:/media";
    }

    //all works correct
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/media/add")
    public ModelAndView addMediaPage(Principal principal, Model model) {
        User user = service.getUserByPrincipal(principal);
        model.addAttribute("isAdmin", user.getRole() == User.Role.ADMIN);
        return new ModelAndView("media-add", model.asMap());
    }

    //all works correct
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/media/edit/{id}")
    public String editContent(@PathVariable("id") UUID id,
                              Principal principal,
                              Model model) {
        User user = service.getUserByPrincipal(principal);
        model.addAttribute("content", service.findContentById(id));
        model.addAttribute("isAdmin", user.getRole() == User.Role.ADMIN);
        return "media-edit";
    }

    //all works correct
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/media/edit/{id}")
    public String updateContent(@PathVariable("id") UUID id,
                                @Valid @RequestParam("name") String name,
                                @Valid @RequestParam("description") String description,
                                @Valid @RequestParam("previewImage") MultipartFile image,
                                Principal principal) throws IOException, NoSuchContentException {
        service.updateMediaContent(principal, id, name, description, image);
        return "redirect:/media";
    }

    //all works correct
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/media/{id}/delete")
    public String deleteMediaContent(@PathVariable("id") UUID id,
                                     Principal principal) throws NoSuchContentException {
        User user = service.getUserByPrincipal(principal);
        if (!service.deleteMediaContent(user, id)) {
            return "error";
        }
        return "redirect:/media";
    }
}
