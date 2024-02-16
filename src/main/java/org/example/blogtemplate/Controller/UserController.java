package org.example.blogtemplate.Controller;

import org.example.blogtemplate.DAO.UserRepository;
import org.example.blogtemplate.Entity.User;
import org.example.blogtemplate.Security.BlogSecurity;
import org.example.blogtemplate.Service.UserService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class UserController {
    private UserService userService;

    private BlogSecurity blogSecurity;

    public UserController(UserService userService, BlogSecurity blogSecurity) {
        this.userService = userService;
        this.blogSecurity = blogSecurity;
    }

    @GetMapping("/loginform")
    public String loginPage(){
        return "login";
    }

    @GetMapping("/register")
    public String registerPage(Model theModel){
        theModel.addAttribute("user",new User());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute  User user){
        if(userService.findUserByEmail(user.getEmail()) == null) {
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userService.saveUser(user);
            return "redirect:/loginform";
        }
        return "redirect:/register?error";
    }



}
