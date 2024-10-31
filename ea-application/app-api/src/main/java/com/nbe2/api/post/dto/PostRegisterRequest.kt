package com.nbe2.api.post.dto

import com.nbe2.domain.posts.City

data class PostRegisterRequest(val title: String, val content: String, val city: City) {
}
