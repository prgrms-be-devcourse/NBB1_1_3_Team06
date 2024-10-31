package com.nbe2.domain.posts

import com.nbe2.common.dto.PageResult
import com.nbe2.domain.posts.exception.PostNotFoundException
import com.nbe2.domain.user.User
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Component

@Component
class PostReader(private val postRepository: PostRepository) {

    fun read(id: Long): Post {
        return postRepository.findById(id)
            .orElseThrow { PostNotFoundException.EXCEPTION }
            ?: throw IllegalArgumentException("Post repository is not available.")
    }

    fun readDetail(id: Long?): Post {
        return postRepository.findDetailById(id)
            ?.orElseThrow { PostNotFoundException.EXCEPTION }
            ?: throw IllegalArgumentException("Post repository is not available.")
    }

    fun readWithPessimisticWriteLock(id: Long?): Post {
        return postRepository
            .findByIdWithPessimisticWriteLock(id)
            ?.orElseThrow { PostNotFoundException.EXCEPTION }
            ?: throw IllegalArgumentException("Post repository is not available.")
    }

    fun readListPage(pageRequest: PageRequest?, city: City?): PageResult<PostListInfo>? {
        val postPage = postRepository.findByCity(city, pageRequest)
        return if (postPage != null) {
            PageResult(mapToInfo(postPage), postPage.totalPages, postPage.hasNext())
        }else
            null
    }

    fun readListPage(pageRequest: PageRequest?, user: User?): PageResult<PostListInfo>? {
        val postPage = postRepository.findByUser(user, pageRequest)
        return if (postPage != null) {
            PageResult(mapToInfo(postPage), postPage.totalPages, postPage.hasNext())
        }else
            null
    }

    private fun mapToInfo(postPage: Page<Post?>?): List<PostListInfo> {
        // postPage가 null인지 확인하고 null일 경우 빈 리스트를 반환
        return postPage?.content?.map { post ->
            PostListInfo(
                post!!.id!!,
                post.writerName ?: "Unknown", // post가 null일 경우 기본값 설정
                post.title ?: "No Title", // 기본값 설정
                post.content ?: "No Content", // 기본값 설정
                post.likeCount ?: 0, // 기본값 설정
                post.commentCount ?: 0 // 기본값 설정
            )
        } ?: emptyList() // postPage가 null일 경우 빈 리스트 반환
    }
}
