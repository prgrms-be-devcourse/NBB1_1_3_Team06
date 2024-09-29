package com.nbe2.domain.posts.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

import com.nbe2.common.dto.PageResult;
import com.nbe2.domain.posts.service.component.PostAppender;
import com.nbe2.domain.posts.service.component.PostDeleter;
import com.nbe2.domain.posts.service.component.PostReader;
import com.nbe2.domain.posts.service.component.PostUpdater;
import com.nbe2.domain.posts.service.dto.*;

@Service
@Transactional
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostAppender postAppender;
    private final PostReader postReader;
    private final PostUpdater postUpdater;
    private final PostDeleter postDeleter;

    @Override
    public Long savePost(final PostRegisterCommand command) {
        return postAppender.append(command);
    }

    @Override
    @Transactional(readOnly = true)
    public PageResult<PostListCommand> findPostListPageByCity(final LocalPostPageCommand command) {
        return postReader.readPostListPageByCity(command);
    }

    @Override
    @Transactional(readOnly = true)
    public PostDetailsCommand findPostDetails(final Long postsId) {
        return postReader.readPostDetails(postsId);
    }

    @Override
    public Long updatePost(final PostUpdateCommand command) {
        return postUpdater.postUpdate(command);
    }

    @Override
    public void deletePost(final Long postsId) {
        postDeleter.deletePost(postsId);
    }
}
