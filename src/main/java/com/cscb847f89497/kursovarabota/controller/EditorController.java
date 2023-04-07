package com.cscb847f89497.kursovarabota.controller;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.cscb847f89497.kursovarabota.dto.EditorDTO;
import com.cscb847f89497.kursovarabota.security.MyUserDetails;
import com.cscb847f89497.kursovarabota.service.EditorService;

@Controller
public class EditorController {

    @Autowired
    private EditorService editorService;

    @GetMapping("/editors")
    private String editors(Model model) {
        model.addAttribute("editors", editorService.list());
        return "editor/editors";
    }

    @GetMapping("/editor/{username}")
    private String editor(@PathVariable("username") String username, Model model) {
        MyUserDetails principal = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal.getUsername().equals(username)) {
            model.addAttribute("editor", editorService.findOneByUsername(username));
        } else {
            return null;
        }
        return "editor/editor";
    }

    @GetMapping("/add/editor")
    private String showFromAddEditor(Model model) {
        model.addAttribute("editor", new EditorDTO());
        return "editor/new-editor";
    }

    @PostMapping("/add/editor")
    public String add(@ModelAttribute EditorDTO editor, Model model) {
        editorService.add(editor);
        return "redirect:/editors";
    }

    @PostMapping("/update/editor")
    public String update(@ModelAttribute EditorDTO editor, Model model) {
        editorService.update(editor);
        return "redirect:/editors/";
    }

    @GetMapping("/delete/editor/{id}")
    public String delete(@PathVariable("id") String stdId, Model model) {
        Long id = Long.parseLong(stdId);
        editorService.delete(id);
        return "redirect:/editors";
    }

    @GetMapping("/edit/editor/{id}")
    public String edit(@PathVariable("id") String stdId, Model model) {
        Long id = Long.parseLong(stdId);
        model.addAttribute("editor", editorService.findOneById(id));
        return "editor/edit-editor";
    }

}
