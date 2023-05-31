package org.example.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
//
//@ControllerAdvice
//public class GlobalExceptionHandler {
//
////    @ExceptionHandler(Exception.class)
////    public ModelAndView handleException(Exception ex) {
////        ModelAndView modelAndView = new ModelAndView("error");
////        modelAndView.addObject("errorMessage", ex.getMessage());
////        return modelAndView;
////    }
//}
