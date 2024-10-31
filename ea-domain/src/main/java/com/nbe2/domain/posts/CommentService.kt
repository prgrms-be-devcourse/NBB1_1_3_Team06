package com.nbe2.domain.posts

import com.nbe2.domain.user.User
import com.nbe2.domain.user.UserReader
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class CommentService (
    private val userReader: UserReader,
    private val postReader: PostReader,
    private val commentAppender: CommentAppender,
    private val commentReader: CommentReader,
    private val commentUpdater: CommentUpdater,
    private val commentDeleter: CommentDeleter,
    private val commentValidator: CommentValidator,
    private val eventPublisher: CommentEventPublisher
){
    fun save(postId: Long?, writeInfo: CommentWriteInfo): Long? {
        val post = postReader.readWithPessimisticWriteLock(postId)
        val user: User = userReader.read(writeInfo.userId)
        commentAppender.append(post, user, writeInfo.commentInfo)
        post.increaseCommentCount()
        eventPublisher.publish(NewCommentEvent.from(post))
        return postId
    }

    @Transactional(readOnly = true)
    fun getPostComments(postId: Long?): List<CommentReadInfo> {
        val post = postReader.read(postId!!)
        val comments = commentReader.read(post)
        return comments!!.stream().map { comment: Comment? ->
            CommentReadInfo.from(comment)
        }.toList()
    }

    fun update(commentsId: Long, writeInfo: CommentWriteInfo): Long? {
        val comment = commentReader.read(commentsId)
        commentValidator.isOwnerId(writeInfo.userId, comment)
        commentUpdater.update(comment, writeInfo.commentInfo)
        return comment.postId
    }

    fun delete(commentsId: Long) {
        val comment = commentReader.read(commentsId)
        val post = postReader.readWithPessimisticWriteLock(comment.postId)
        commentDeleter.delete(comment)
        post.decreaseCommentCount()
    }
}
