package com.azxca1731.timeliner.controller;

import com.azxca1731.timeliner.domain.Post;
import com.azxca1731.timeliner.domain.User;
import com.azxca1731.timeliner.service.PostService;
import com.azxca1731.timeliner.service.SecurityService;
import com.azxca1731.timeliner.service.UserService;
import lombok.extern.slf4j.Slf4j;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RequestMapping("/")
@Controller
//Session - 로그인
//FormData 사진같은거
//Pagination
//Validation
//Template
@Slf4j
public class PostController {

    @Autowired
    PostService postService;

    @Autowired
    UserService userService;

    @Autowired
    SecurityService securityService;

    @RequestMapping("/login")
    public String login(Model model, String error, String logout, HttpServletRequest request ){
        if (logout != null){
            model.addAttribute("logout", "You have been logged out successfully.");
        }
        return "login";
    }

    // 로그인 실패시
    @RequestMapping(value="/loginError")
    public String loginError(Model model, String username ){
        model.addAttribute("error", "Your username and password is invalid.");
        model.addAttribute("username",username);
        return "login";
    }

    // 회원가입폼
    @RequestMapping(value="/registration",method=RequestMethod.GET)
    public String registration(Model model){
        model.addAttribute("userForm", new User());
        return "registration";
    }

    @RequestMapping(value="/registration",method=RequestMethod.POST)
    public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult,
                               Model model ,String[] roles ){
        String password = userForm.getPassword();
        userService.saveUser(userForm,roles);
        securityService.autologin(userForm.getEmail(),password);
        return "redirect:/";
    }

    @GetMapping
    public String showAllPost(Model model) {
        org.springframework.security.core.userdetails.User loginedUser = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(loginedUser instanceof org.springframework.security.core.userdetails.User) {
            System.out.println(loginedUser.getUsername());
        }
        model.addAttribute("posts",postService.findAllPost());
        return "main";
    }

    @GetMapping("{idx}")
    public String showPostDetail(@PathVariable long idx, Model model) {
        model.addAttribute("post",postService.findPostByIdx(idx));
        return "hello";
    }

    @GetMapping("new")
    public String showCreatePostForm(Model model) {
        return "form";
    }

    @PostMapping
    public String createPost(@ModelAttribute(value = "post") Post newPost){
        postService.saveNewPost(newPost);
        return "redirect:/"+newPost.getIdx();
    }

}
