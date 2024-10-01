package com.nbe2.domain.posts.entity;

import jakarta.persistence.*;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import com.nbe2.domain.global.BaseTimeEntity;
import com.nbe2.domain.user.User;

@Entity
@Getter
@Table(name = "comments")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment extends BaseTimeEntity {

    @Id
    @GeneratedValue
    @Column(name = "comment_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private String content;

    @Builder
    private Comment(final Post post, final User user, final String content) {
        this.post = post;
        this.user = user;
        this.content = content;
    }

    public Long getPostId() {
        return post.getId();
    }

    public Long getUserId() {
        return user.getId();
    }

    public String getUserName() {
        return user.getName();
    }

    // business logic **//
    public Long update(final String content) {
        this.content = content;
        return id;
    }
}
