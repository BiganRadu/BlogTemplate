package org.example.blogtemplate.Controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.HttpClientErrorException;

@Controller
public class BlogErrorController implements ErrorController {
    @RequestMapping("/error")
    public String handleError(HttpServletRequest request, Model theModel){
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        theModel.addAttribute("code", status.toString());
        int statusCode = Integer.parseInt(status.toString());
        switch(statusCode){
            case 404: {
                theModel.addAttribute("message", "PAGE NOT FOUND");
                break;
            }
            case 403:{
                theModel.addAttribute("message", "FORBIDDEN");
                break;
            }
            case 500: {
                theModel.addAttribute("message", "INTERNAL SERVER ERROR");
                break;
            }
            default:{
                theModel.addAttribute("message", "UNKNOWN ERROR");
                break;
            }

        }
        return "error";
    }
}
