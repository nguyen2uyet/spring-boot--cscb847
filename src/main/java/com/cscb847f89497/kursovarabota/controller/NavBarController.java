package com.cscb847f89497.kursovarabota.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.cscb847f89497.kursovarabota.dto.ArticleDTO;
import com.cscb847f89497.kursovarabota.dto.EmailDetails;
import com.cscb847f89497.kursovarabota.dto.MessageDTO;
import com.cscb847f89497.kursovarabota.service.ArticleService;
import com.cscb847f89497.kursovarabota.service.EmailService;

@Controller
public class NavBarController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private EmailService emailService;

    @GetMapping("/home")
    String home(Model model) {
        return "redirect:/cscb847";
    }

    @GetMapping("/languages")
    String languages(Model model) {
        List<ArticleDTO> articles = articleService.findByNavbar("foreign-language");
        articles.addAll(articleService.findByNavbar("bulgarian-language"));
        // articles.stream().forEachOrdered(articleService.findByNavbar("bulgarian-language")::add);
        model.addAttribute("articles", articles);
        return "/navbar/languages";
    }

    @GetMapping("/languages/{subdomain}")
    private String languagesSubdomain(@PathVariable("subdomain") String subdomain, Model model) {
        model.addAttribute("article", articleService.findOneBySubdomain(subdomain));
        return "/navbar/blog";
    }

    @GetMapping("/programs")
    String programs(Model model) {
        List<ArticleDTO> articles = articleService.findByNavbar("current-programs");
        articles.addAll(articleService.findByNavbar("soon-programs"));
        model.addAttribute("articles", articles);
        return "/navbar/programs";
    }

    @GetMapping("/programs/{subdomain}")
    private String programsSubdomain(@PathVariable("subdomain") String subdomain, Model model) {
        model.addAttribute("article", articleService.findOneBySubdomain(subdomain));
        return "/navbar/blog";
    }

    @GetMapping("/about-us")
    String aboutUs(Model model) {
        model.addAttribute("articles", articleService.findByNavbar("about-us"));
        return "/navbar/about-us";
    }

    @GetMapping("/about-us/{subdomain}")
    private String aboutUsSubdomain(@PathVariable("subdomain") String subdomain, Model model) {
        model.addAttribute("article", articleService.findOneBySubdomain(subdomain));
        return "/navbar/blog";
    }

    @GetMapping("/contact")
    public String contact(Model model) {
        model.addAttribute("message", new MessageDTO());
        return "/navbar/contact";
    }

    @PostMapping("/contact/message")
    public String getMessage(@ModelAttribute MessageDTO messageDTO, Model model) {
        EmailDetails emailDetails = new EmailDetails();
        emailDetails.setSubject(messageDTO.getName() + " "
                + messageDTO.getEmail() + " "
                + messageDTO.getSubject());
        emailDetails.setMsgBody(messageDTO.getContent());
        emailDetails.setRecipient("kyetnguyen@abv.bg");
        emailService.sendSimpleMail(emailDetails);
        return "redirect:/contact";
    }

    @GetMapping("/location")
    String location(Model model) {
        return "/navbar/location";
    }

    @GetMapping("/current-programs")
    String currentlyProgram(Model model) {
        model.addAttribute("articles", articleService.findByNavbar("current-programs"));
        return "/navbar/current-programs";
    }

    @GetMapping("/current-programs/{subdomain}")
    private String currentlyProgramSubdomain(@PathVariable("subdomain") String subdomain, Model model) {
        model.addAttribute("article", articleService.findOneBySubdomain(subdomain));
        return "/navbar/blog";
    }

    @GetMapping("/soon-programs")
    String soonProgram(Model model) {
        model.addAttribute("articles", articleService.findByNavbar("soon-program"));
        return "/navbar/soon-programs";
    }

    @GetMapping("/soon-programs/{subdomain}")
    private String soonProgramSubdomain(@PathVariable("subdomain") String subdomain, Model model) {
        model.addAttribute("article", articleService.findOneBySubdomain(subdomain));
        return "/navbar/blog";
    }

    @GetMapping("/lastyear-courses")
    String lastyearCourses(Model model) {
        model.addAttribute("articles", articleService.findByNavbar("current-programs"));
        return "/navbar/lastyear-courses";
    }

    @GetMapping("/lastyear-courses/{subdomain}")
    private String lastyearCoursesSubdomain(@PathVariable("subdomain") String subdomain, Model model) {
        model.addAttribute("article", articleService.findOneBySubdomain(subdomain));
        return "/navbar/blog";
    }

    @GetMapping("/foreign-language")
    String foreginLanguage(Model model) {
        model.addAttribute("articles", articleService.findByNavbar("foreign-language"));
        return "/navbar/foreign-language";
    }

    @GetMapping("/foregin-language/{subdomain}")
    private String foreginLanguageSubdomain(@PathVariable("subdomain") String subdomain, Model model) {
        model.addAttribute("article", articleService.findOneBySubdomain(subdomain));
        return "/navbar/blog";
    }

    @GetMapping("/bulgarian-language")
    String bulgarianLanguage(Model model) {
        model.addAttribute("articles", articleService.findByNavbar("bulgarian-language"));
        return "/navbar/bulgarian-language";
    }

    @GetMapping("/bulgarian-language/{subdomain}")
    private String bulgarianLanguageSubdomain(@PathVariable("subdomain") String subdomain, Model model) {
        model.addAttribute("article", articleService.findOneBySubdomain(subdomain));
        return "/navbar/blog";
    }

}
