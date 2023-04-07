package com.cscb847f89497.kursovarabota.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.cscb847f89497.kursovarabota.security.MyUserDetails;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(Model model) {
        MyUserDetails principal = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal.isEditor() || principal.isAdmin()) {
            return"redirect:/articles";
        }
        return "redirect:/cscb847";
    }

    @GetMapping("/cscb847")
    public String cscb847(Model model) {
        return "/blog/index";
    }

}
