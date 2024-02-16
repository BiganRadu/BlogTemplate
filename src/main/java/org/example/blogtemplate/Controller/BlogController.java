package org.example.blogtemplate.Controller;

import jakarta.annotation.PostConstruct;
import org.example.blogtemplate.Entity.Blog;
import org.example.blogtemplate.Entity.User;
import org.example.blogtemplate.Service.BlogService;
import org.example.blogtemplate.Service.BlogUserDetails;
import org.example.blogtemplate.Service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/")
public class BlogController {
    private BlogService blogService;

    private UserService userService;
    public BlogController(BlogService blogService, UserService userService){
        this.blogService = blogService;
        this.userService = userService;
    }

  /*  @PostConstruct
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
    }*/
    @GetMapping("/")
    public String main_page(Model theModel){
        List<Blog> blogArray = blogService.getAllBlogs();
        List<String> userNames = new ArrayList<>();
        for(Blog blog : blogArray){
            Optional<User> optionalUser = userService.getUserById(blog.getAuthorId());
            if(optionalUser.isPresent()){
                userNames.add(optionalUser.get().getUserName());
            }else{
                userNames.add("NULL");
            }
        }
        theModel.addAttribute("blogs", blogArray);
        theModel.addAttribute("names", userNames);
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

    @GetMapping("/contact")
    public String contact_page(){
        return "contact";
    }

    @GetMapping("/addpost")
    public String add_post_get(Model theModel){
        theModel.addAttribute("blog",new Blog());
        return "addpost";
    }

    @GetMapping("/loginform")
    public String loginPage(){
        return "login";
    }

    @GetMapping("/register")
    public String registerPage(){
        return "register";
    }

   @PostMapping("/addpost")
    public String addPost(@ModelAttribute Blog blog){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(!(authentication instanceof AnonymousAuthenticationToken)) {
            blog.setDate(new Date());
            BlogUserDetails userDetails = (BlogUserDetails)authentication.getPrincipal();
            blog.setAuthorId(userDetails.getUser().getId());
            blogService.addBlog(blog);
        }
        return "redirect:/";
    }

    @GetMapping("/editpost/{id}")
    public String editPostGet(Model theModel, @PathVariable Integer id){
        Optional<Blog> optionalBlog = blogService.getBlogById(id);
        Blog blog = optionalBlog.get();
        theModel.addAttribute("blog",blog);
        return "editpost";
    }

    @PostMapping("/editpost/{id}")
    public String editPost(@ModelAttribute Blog blog, @PathVariable Integer id){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(!(authentication instanceof AnonymousAuthenticationToken)) {
            BlogUserDetails userDetails = (BlogUserDetails)authentication.getPrincipal();
            Optional<Blog> blogOptional = blogService.getBlogById(id);
            Blog existBlog = blogOptional.get();
            if(userDetails.getUser().getId() == existBlog.getAuthorId()){
                existBlog.setTitle(blog.getTitle());
                existBlog.setDescription(blog.getDescription());
                existBlog.setImageHeader(blog.getImageHeader());
                existBlog.setText(blog.getText());
                blogService.addBlog(existBlog);
                System.out.println("Updated blog with id " + existBlog.getId());
            }
        }
        return "redirect:/";
    }

    @PostMapping("/delete/{id}")
    public String deletePost(@PathVariable Integer id){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            BlogUserDetails userDetails = (BlogUserDetails) authentication.getPrincipal();
            Optional<Blog> blogOptional = blogService.getBlogById(id);
            Blog blog  = blogOptional.get();
           if(userDetails.getUser().hasRole("ADMIN") || userDetails.getUser().getId() == blog.getAuthorId()){
                System.out.println("Deleted blog with id " + id);
                blogService.deleteBlog(id);
            }
        }
        return "redirect:/";
    }
}
