package com.nbe3.domain.posts

import com.nbe3.common.dto.Page
import com.nbe3.common.dto.PageResult
import com.nbe3.domain.global.util.PagingUtil
import jdk.vm.ci.meta.ValueKind.Illegal
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class PostService(
    private val postAppender: PostAppender,
    private val postReader: PostReader,
    private val postUpdater: PostUpdater,
    private val postDeleter: PostDeleter,
    private val userReader: PostReader
){
    @Transactional
    fun save(userId: Long?, postWriteInfo: PostWriteInfo?): Long? {
        val user: User = userReader.read(userId)
        return postWriteInfo?.let { postAppender.append(user, it) }
    }

    fun findListPageByCity(page: Page?, city: City?): PageResult<PostListInfo>? {
        return postReader.readListPage(PagingUtil.toPageRequest(page), city)
    }

    fun getUserPostPages(page: Page?, userId: Long?): PageResult<PostListInfo>? {
        val user: User = userReader.read(userId)
        return postReader.readListPage(PagingUtil.toPageRequest(page), user)
    }

    fun findDetails(postsId: Long?): PostDetailsInfo {
        val post = postReader.readDetail(postsId)
        return PostDetailsInfo.from(post)
    }

    @Transactional
    fun update(postsId: Long?, postWriteInfo: PostWriteInfo?): Long? {
        val post = postReader.read(postsId ?: throw IllegalArgumentException("Post Id cannot be null"))
        return postUpdater.update(post, postWriteInfo!!)
    }

    @Transactional
    fun delete(postsId: Long?) {
        postDeleter.delete(postsId)
    }
}