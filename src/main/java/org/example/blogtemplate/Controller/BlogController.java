package org.example.blogtemplate.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class BlogController {
    @GetMapping("/")
    public String main_page(Model theModel){
        return "index";
    }
}
