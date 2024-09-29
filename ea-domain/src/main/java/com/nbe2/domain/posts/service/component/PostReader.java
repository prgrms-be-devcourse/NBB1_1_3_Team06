package com.nbe2.domain.posts.service.component;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

import com.nbe2.common.dto.PageResult;
import com.nbe2.domain.global.util.PagingUtil;
import com.nbe2.domain.posts.entity.Post;
import com.nbe2.domain.posts.exception.PostNotFoundException;
import com.nbe2.domain.posts.repository.PostRepository;
import com.nbe2.domain.posts.service.dto.LocalPostPageCommand;
import com.nbe2.domain.posts.service.dto.PostDetailsCommand;
import com.nbe2.domain.posts.service.dto.PostListCommand;

@Component
@RequiredArgsConstructor
public class PostReader {
    private final PostRepository postRepository;

    public PageResult<PostListCommand> readPostListPageByCity(final LocalPostPageCommand command) {
        Page<Post> postPage =
                postRepository.findByCity(command.city(), PagingUtil.toPageRequest(command.page()));
        List<PostListCommand> postCommands =
                postPage.getContent().stream()
                        .map(
                                p ->
                                        new PostListCommand(
                                                p.getId(),
                                                p.getUser().getName(),
                                                p.getTitle(),
                                                p.getContent()))
                        .toList();
        return new PageResult<>(postCommands, postPage.getTotalPages(), postPage.hasNext());
    }

    public PostDetailsCommand readPostDetails(final Long postsId) {
        Post post =
                postRepository.findById(postsId).orElseThrow(() -> PostNotFoundException.EXCEPTION);
        return PostDetailsCommand.from(post);
    }
}
