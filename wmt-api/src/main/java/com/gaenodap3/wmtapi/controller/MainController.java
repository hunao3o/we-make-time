package com.gaenodap3.wmtapi.controller;

import java.util.Objects;

import com.gaenodap3.wmtapi.domain.Posts;
import com.gaenodap3.wmtapi.domain.PostsRepository;
import com.gaenodap3.wmtapi.dto.PostsSaveRequestDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class MainController {

    @Autowired
    private PostsRepository postsRepository;

    @GetMapping("/{name}.html")
    public String main(@PathVariable String name, Model model) {
        model.addAttribute("pageName", name);
        return "index";
    }

    @PostMapping("/posts")
    public Posts savePosts(@RequestBody PostsSaveRequestDto dto) {
        return postsRepository.save(dto.toEntity());
    }
}