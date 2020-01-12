package com.gaenodap3.wmtapi.controller;

import java.util.HashMap;
import java.util.Objects;
import java.util.stream.Collector;

import com.gaenodap3.wmtapi.domain.Posts;
import com.gaenodap3.wmtapi.domain.PostsRepository;
import com.gaenodap3.wmtapi.dto.PostsSaveRequestDto;

import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {

    @Autowired
    private PostsRepository postsRepository;
    
    @GetMapping("/{name}.html")
    public String main(@PathVariable String name, Model model) {
        model.addAttribute("pageName", name);
        return "index";
    }

    @ResponseBody
    @GetMapping("/posts")
    public Posts loadPosts(@RequestParam Long id) {
        StopWatch watch = new StopWatch();
        watch.start();
        Posts result = this.getPosts(id);
        watch.stop();
        System.out.println("time spent : " + watch.getTotalTimeMillis());
        return result;
    }

    @ResponseBody
    @GetMapping("/allPosts")
    public boolean loadAllPosts(){
        StopWatch watch = new StopWatch();
        watch.start();
        this.getPostsMap();
        watch.stop();
        System.out.println("[all] time spent : " + watch.getTotalTimeMillis());
        return true;
    }

    @CachePut(value = "postCache")
    private void getPostsMap() {
        postsRepository.findAll();
    }
    
    @Cacheable(value = "postCache", key = "#id")
    private Posts getPosts(Long id) {
        return postsRepository.findById(id).orElse(new Posts("","",""));
    }

    @ResponseBody
    @PostMapping("/posts")
    public Posts savePosts(@RequestBody PostsSaveRequestDto dto) {
        return postsRepository.save(dto.toEntity());
    }
}