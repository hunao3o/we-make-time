package com.gaenodap3.wmtapi.service.impl;

import com.gaenodap3.wmtapi.domain.Posts;
import com.gaenodap3.wmtapi.domain.PostsRepository;
import com.gaenodap3.wmtapi.dto.PostsSaveRequestDto;
import com.gaenodap3.wmtapi.service.PostsService;

import org.springframework.cache.annotation.Cacheable;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PostsServiceImpl implements PostsService {

    private PostsRepository postsRepository;

    public PostsServiceImpl(PostsRepository postsRepository){
        this.postsRepository = postsRepository;
    }

    @Override
    @Cacheable(cacheNames = {"CACHE_POST_BY_ID"}, key = "#id")
    public Posts getPosts(Long id) {
        return postsRepository.findById(id).orElse(new Posts("none", "none", "none"));
    }

    @Override
    public boolean savePosts(PostsSaveRequestDto req) {
        try {
            Posts saved = postsRepository.save(req.toEntity());
            System.out.println(saved.toString());
            return true;
        } catch (Exception e) {
            System.out.println("[/savePost] saving post fail !! error : " + e.getStackTrace());
        }
        return false;
    }

    @Override
    public boolean deletePosts(Long id) {
        postsRepository.deleteById(id);
        return true;
    }

    @Override
    public boolean deleteAllPosts() {
        postsRepository.deleteAll();
        return true;
    }

}