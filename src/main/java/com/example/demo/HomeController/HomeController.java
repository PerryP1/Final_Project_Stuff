package com.example.demo.HomeController;

import com.example.demo.Models.Message;
import com.example.demo.Models.User;
import com.example.demo.Repositories.MessageRepository;
import com.example.demo.Repositories.UserRepository;
import com.example.demo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;

@Controller
public class HomeController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MessageRepository messageRepository;

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String showRegistrationPage(Model model) {
        model.addAttribute("user", new User());
        return "registration";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String processRegistrationPage(
            @Valid @ModelAttribute("user") User user,
            BindingResult result,
            Model model) {

        model.addAttribute("user", user);
        if (result.hasErrors()) {
            return "registration";
        } else {
            userService.saveUser(user);
            model.addAttribute("message", "User Account Successfully Created");
        }
        return "index1";
    }

    @RequestMapping("/")
    public String index() {
        return "index1";
    }

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping("/secure")
    public String secure(HttpServletRequest request,
                         Authentication authentication,
                         Principal principal) {
        Boolean isAdmin = request.isUserInRole("ADMIN");
        Boolean isUser = request.isUserInRole("USER");
        UserDetails userDetails = (UserDetails)
                authentication.getPrincipal();
        String username = principal.getName();
        return "secure";

    }

    private User getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentusername = authentication.getName();
        User user = userRepository.findByUsername(currentusername);
        return user;
    }

    @GetMapping("/add")
    public String messageForm(Model model){
//        Creating new message and adding to model
        model.addAttribute("message", new Message());
        return "messageform";
    }
    @PostMapping("/process")
    public String processForm(@Valid Message message, BindingResult result)
    {
//        Checking message for errors and redirecting to index page
        if(result.hasErrors()){
            return "messageform";
        }
//        Saving message to repo h2 database
        messageRepository.save(message);
        return "redirect:/";

    }

    @RequestMapping("/list")
    public String showMessages(Model model){
        model.addAttribute("messages", messageRepository.findAll());
        return "list";
    }
    @RequestMapping("/content/{id}")
    public String showContent(@PathVariable("id") long id, Model model) {
        model.addAttribute("message", messageRepository.findById(id));
        return "show";
    }
    @RequestMapping("/posteddate/{id}")
    public String showDate(@PathVariable("id") long id, Model model) {
        model.addAttribute("message", messageRepository.findById(id));
        return "messageform";
    }


}