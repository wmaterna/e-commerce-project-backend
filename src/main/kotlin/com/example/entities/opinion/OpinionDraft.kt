package com.example.entities.opinion

import com.example.entities.product.Product
import com.example.entities.user.User

data class OpinionDraft(
    val user: User,
    val product: Product,
    val content: String
)
