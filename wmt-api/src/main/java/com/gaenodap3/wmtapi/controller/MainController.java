package com.gaenodap3.wmtapi.controller;

import com.gaenodap3.wmtapi.domain.Posts;
import com.gaenodap3.wmtapi.domain.PostsRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {

    @Autowired
    private PostsRepository postsRepository;

    // @Autowired
    // private PostsService postsService;

    @GetMapping("/{name}.html")
    public String main(@PathVariable String name, Model model) {
        model.addAttribute("pageName", name);
        return "index";
    }

    @ResponseBody
    @GetMapping("/posts")
    public Posts loadPosts(@RequestParam Long id){
        StopWatch watch = new StopWatch();
        watch.start();
        // Posts result = postsService.getPosts(id);
        Posts result =this.getPost(id);
        watch.stop();
        System.out.println("time spent : " + watch.getTotalTimeMillis());
        return result;
    }
    
    @Cacheable(cacheNames = {"CACHE_POST_BY_ID"}, key = "#id")
    private Posts getPost(Long id) {
        return postsRepository.findById(id).orElse(new Posts("none", "none", "none"));
    }
    
    // @ResponseBody
    // @PostMapping("/posts")
    // public boolean savePosts(@RequestBody PostsSaveRequestDto request) {
    //     return postsService.savePosts(request);
    // }
}