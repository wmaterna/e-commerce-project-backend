package com.example.entities.opinion


import com.example.entities.user.UserOpinionInfo


data class Opinion(
    val id: Int,
    val user: List<UserOpinionInfo>,
    val product: Int,
    val content: String
)
