package com.nbe2.api.post.dto

import com.nbe2.domain.posts.City

class PostUpdateRequest(val title: String, val content: String, val city: City) {

}
