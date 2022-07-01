package com.example.entities.opinion

import com.example.entities.product.Product
import com.example.entities.user.User

data class OpinionDraft(
    val product: Int,
    val content: String
)
