package org.example.blogtemplate.Controller;

import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.annotation.PostConstruct;
import org.example.blogtemplate.Entity.Blog;
import org.example.blogtemplate.Entity.Comment;
import org.example.blogtemplate.Entity.Mess;
import org.example.blogtemplate.Entity.User;
import org.example.blogtemplate.Service.BlogService;
import org.example.blogtemplate.Service.BlogUserDetails;
import org.example.blogtemplate.Service.CommentService;
import org.example.blogtemplate.Service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.mail.Session;
import javax.mail.Transport;
import javax.swing.text.html.Option;

import java.util.*;

@Controller
@RequestMapping("/")
public class BlogController {
    private BlogService blogService;

    private UserService userService;

    private CommentService commentService;
    public BlogController(BlogService blogService, UserService userService, CommentService commentService){
        this.blogService = blogService;
        this.userService = userService;
        this.commentService = commentService;
    }


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
            theModel.addAttribute("comm", new Comment());
            return "post";
        }
        return "index";
    }

    @GetMapping("/contact")
    public String contactPage(Model theModel){
        theModel.addAttribute("mess", new Mess());
        return "contact";
    }

    @PostMapping("/contact")
    public String contact(@ModelAttribute Mess mess){
        String recipient = "radubigan5@gmail.com";

        String username = "blogpost49@gmail.com";
        String password = "jhpb xkyo ueyp eggo";

        Properties props = System.getProperties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.ssl.protocols", "TLSv1.2");
        props.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });
        try
        {
            MimeMessage message = new MimeMessage(session);

            message.setFrom(new InternetAddress(username));

            message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));

            message.setSubject("BLOG POST CONTACT: " + mess.getSubject());

            message.setText("Username : " + mess.getSender() + "\nUser email : " + mess.getSenderEmail() + "\nContent: \n" + mess.getContent());

            Transport.send(message);
            System.out.println("Mail successfully sent");
        }
        catch (MessagingException mex)
        {
            mex.printStackTrace();
        }
        return "redirect:/";
    }

    @GetMapping("/addpost")
    public String add_post_get(Model theModel){
        theModel.addAttribute("blog",new Blog());
        return "addpost";
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

    @PostMapping("/addcomment/{id}")
    public String addComment(@ModelAttribute Comment comment,@PathVariable Integer id){
        Optional<Blog> blogOptional = blogService.getBlogById(id);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken) && blogOptional.isPresent()){
            BlogUserDetails userDetails = (BlogUserDetails) authentication.getPrincipal();
            comment.setPostId(id);
            comment.setDate(new Date());
            System.out.println(userDetails.getUser().getId());
            comment.setAuthorId(userDetails.getUser().getId());
            commentService.saveComment(comment);
        }
        return "redirect:/blog/" + id;
    }

    @PostMapping("/delete/{id}")
    public String deleteComment(@PathVariable Integer id){
        Optional<Comment> optionalComment = commentService.getCommentById(id);
        if(optionalComment.isPresent()){
            commentService.deleteCommentById(id);
        }
        return "/blog/" + optionalComment.get().getPostId();
    }
    @GetMapping("/about")
    public String aboutPage(){
        return "about";
    }
}
