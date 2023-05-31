package org.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.autoconfigure.web.servlet.error.AbstractErrorController;
import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;

import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;


@Controller
public class CustomErrorController extends BasicErrorController implements ErrorController, AccessDeniedHandler {

    private static final String ERROR_PATH = "/error";

    public CustomErrorController(ErrorAttributes errorAttributes, ServerProperties serverProperties) {
        super(errorAttributes, serverProperties.getError());
    }
//    @GetMapping(ERROR_PATH)
//    public String handleError() {
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        if (auth == null) {
//            return "redirect:/default";
//        } else {
//            Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();
//            if (authorities.contains(new SimpleGrantedAuthority("ADMIN"))) {
//                return "redirect:/admin/dashboard";
//            }else {
//                return "redirect:/user/dashboard";
//            }
//        }
//    }

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null) {
            response.sendRedirect("/default");
        } else {
            Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();
            if (authorities.contains(new SimpleGrantedAuthority("ADMIN"))) {
                adminDashboard();
                response.sendRedirect("/admin/dashboard");
            } else {
                response.sendRedirect("/user/dashboard");
            }
        }

    }

    @RequestMapping("/admin/dashboard")
    public String adminDashboard() {
        return "admin-dashboard.html";
    }

    @RequestMapping("/user/dashboard")
    public String userDashboard() {
        return "user-dashboard";
    }

    @RequestMapping("/default")
    public String defaultPage() {
        return "default-page";
    }

}
