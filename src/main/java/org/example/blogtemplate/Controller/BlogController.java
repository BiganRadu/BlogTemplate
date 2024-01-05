package org.example.blogtemplate.Controller;

import jakarta.annotation.PostConstruct;
import org.example.blogtemplate.Entity.Blog;
import org.example.blogtemplate.Service.BlogService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.net.URI;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/")
public class BlogController {
    private BlogService blogService;
    public BlogController(BlogService blogService){
        this.blogService = blogService;
    }

    @PostConstruct
    public void init(){
        Blog first = new Blog();
        //first.setAuthor("RaduZEW");
        first.setTitle("Primul Articol");
        first.setDescription("Acesta este primul articol facut cu thymeleaf!");
        first.setText("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.");
        first.setAuthorId(5);
        first.setDate(new Date());
        first.setImageHeader("https://t3.gstatic.com/licensed-image?q=tbn:ANd9GcQi0-LAUlcpsSu4RzEL0hhwzF66bX5QXXWDz3ZuOSFHgolg7Zeej77cElBAhSJ5Xfcw");
        blogService.addBlog(first);
    }
    @GetMapping("/")
    public String main_page(Model theModel){
        theModel.addAttribute("blogs", blogService.getAllBlogs());
        return "index";
    }
    @GetMapping("/blog/{id}")
    public String post_page(Model theModel,@PathVariable Integer id){
        Optional<Blog> blg = blogService.getBlogById(id);
        if(blg.isPresent()){
            theModel.addAttribute("blog",blg.get());
            System.out.println(blg.get().getTitle());
            return "post";
        }
        return "index";
    }
}
