package com.nbe2.domain.posts.service.component;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

import com.nbe2.domain.posts.entity.Post;
import com.nbe2.domain.posts.exception.PostNotFoundException;
import com.nbe2.domain.posts.repository.PostRepository;

@Component
@RequiredArgsConstructor
public class PostDeleter {
    private final PostRepository postRepository;

    public void deletePost(final Long postsId) {
        Post post =
                postRepository.findById(postsId).orElseThrow(() -> PostNotFoundException.EXCEPTION);
        postRepository.delete(post);
    }
}
