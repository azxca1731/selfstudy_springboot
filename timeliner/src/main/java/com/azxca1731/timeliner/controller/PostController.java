package com.azxca1731.timeliner.controller;

import com.azxca1731.timeliner.domain.Post;
import com.azxca1731.timeliner.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/")
@Controller
//Session - 로그인
//FormData 사진같은거
//Pagination
//Validation
//Template
public class PostController {

    @Autowired
    PostService postService;

    @GetMapping
    public String showAllPost(Model model) {
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
