package com.nbe3.api.post.dto

import com.nbe3.domain.posts.City

data class PostRegisterRequest(val title: String, val content: String, val city: City) {
}
