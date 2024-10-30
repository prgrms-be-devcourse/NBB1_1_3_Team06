package com.nbe3.domain.posts

import com.nbe3.domain.user.User
import com.nbe3.domain.user.UserReader
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class LikeService(
    private val postReader: PostReader,

    private val userReader: UserReader,

    private val likeAppender: LikeAppender,

    private val likeDeleter: LikeDeleter,

    private val likeValidator: LikeValidator
) {

    @Transactional
    fun addLike(info: LikeInfo) {
        val post = postReader.readWithPessimisticWriteLock(info.postId)
        val user: User = userReader.read(info.userId)
        if (likeValidator.isExist(post, user)) return
        likeAppender.append(post, user)
        post.increaseLikeCount()
    }

    @Transactional
    fun cancelLike(info: LikeInfo) {
        val post = postReader.readWithPessimisticWriteLock(info.postId)
        val user: User = userReader.read(info.userId)
        if (likeValidator.isExist(post, user)) {
            likeDeleter.delete(post, user)
            post.decreaseLikeCount()
        }
    }
}
