package com.project.msg.controller;

import com.project.msg.service.IndexService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class IndexViewController {

    private final IndexService indexService;

    @GetMapping("/mustache")
    public String index(Model model){
//        model.addAttribute();
        System.out.println("model = " + model);
        return "index";
    }
}
