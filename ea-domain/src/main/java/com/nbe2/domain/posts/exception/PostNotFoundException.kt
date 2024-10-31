package com.nbe2.domain.posts.exception

import com.nbe2.common.exception.DomainException
import com.nbe2.domain.file.exception.FileMetaDataErrorCode


class PostNotFoundException private constructor (errorCode: PostErrorCode) : DomainException(errorCode) {
    companion object {
        val EXCEPTION: DomainException =
            PostNotFoundException(PostErrorCode.POST_NOT_FOUND)
    }
}
//object PostNotFoundException : DomainException(PostErrorCode.POST_NOT_FOUND)