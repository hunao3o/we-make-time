package com.gaenodap3.wmtapi.service;

import com.gaenodap3.wmtapi.domain.Posts;
import com.gaenodap3.wmtapi.dto.PostsSaveRequestDto;

import org.springframework.stereotype.Service;

@Service
public interface PostsService {
    
    Posts getPosts(Long id);

    boolean savePosts(PostsSaveRequestDto req);

    boolean deletePosts(Long id);

    boolean deleteAllPosts();
}