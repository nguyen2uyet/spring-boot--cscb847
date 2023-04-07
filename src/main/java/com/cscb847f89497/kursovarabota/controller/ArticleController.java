package com.cscb847f89497.kursovarabota.controller;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.cscb847f89497.kursovarabota.dto.ArticleDTO;
import com.cscb847f89497.kursovarabota.service.ArticleService;

@Controller
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @GetMapping("/articles")
    private String articles(Model model) {
        model.addAttribute("articles", articleService.list());
        return "article/articles";
    }

    @GetMapping("/article/{subdomain}")
    private String article(@PathVariable("subdomain") String subdomain, Model model) {
        model.addAttribute("article", articleService.findOneBySubdomain(subdomain));
        return "article/article";
    }

    @GetMapping("/add/article")
    private String showFromAddArticle(Model model) {
        model.addAttribute("article", new ArticleDTO());
        model.addAttribute("navbars", articleService.findAllNavbar());
        return "article/new-article";
    }

    @PostMapping("/add/article")
    public String add(@ModelAttribute ArticleDTO article, Model model) {
        articleService.add(article);
        return "redirect:/articles";
    }

    @PostMapping("/update/article")
    public String update(@ModelAttribute ArticleDTO article, Model model) {
        articleService.update(article);
        return "redirect:/articles/";
    }

    @GetMapping("/delete/article/{id}")
    public String delete(@PathVariable("id") String stdId, Model model) {
        Long id = Long.parseLong(stdId);
        articleService.delete(id);
        return "redirect:/articles";
    }

    @GetMapping("/edit/article/{id}")
    public String edit(@PathVariable("id") String stdId, Model model) {
        Long id = Long.parseLong(stdId);
        model.addAttribute("article", articleService.findOneById(id));
        model.addAttribute("navbars", articleService.findAllNavbar());
        return "article/edit-article";
    }

}
