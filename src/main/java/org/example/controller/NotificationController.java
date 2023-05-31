package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.entity.MediaContent;
import org.example.entity.Notification;
import org.example.exceptions.NoSuchContentException;
import org.example.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.security.PermitAll;
import java.security.Principal;
import java.util.List;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService service;

    //all works good
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/profile/notifications")
    public String getAll(Principal principal,
                                     Model model){
        List<Notification> notifications = service.getAll(principal);
        model.addAttribute("notifications", notifications);
        boolean count = notifications.size()!=0;
        model.addAttribute("count", count);
        return "notifications";
    }

    //all works correct
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/profile/notifications/delete/{id}")
    public String deleteNotification(@PathVariable ("id") UUID id){
        service.deleteNotification(id);
        return "redirect:/profile/notifications";
    }

    //all works correct
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/profile/notifications")
    public String deleteAllNotifications(Principal principal){
        service.deleteAll(principal);
        return "redirect:/profile";
    }

    //all works correct
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/profile/check/{id}/accept")
    public String addContent(@PathVariable ("id")UUID id) throws NoSuchContentException {
        service.acceptContent(id);
        return "redirect:/profile/check";
    }

    //all works correct
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/profile/check/{id}/reject")
    public String removeContent(@PathVariable ("id")UUID id) throws NoSuchContentException {
        service.rejectContent(id);
        return "redirect:/profile/check";
    }
}
