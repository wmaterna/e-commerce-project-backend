package com.example.entities.opinion

import com.example.database.dbEntities.DBProductEntity
import com.example.entities.product.Product
import com.example.entities.user.User
import com.example.entities.user.UserOpinionInfo
import org.ktorm.dsl.Query

data class Opinion(
    val id: Int,
    val user: List<UserOpinionInfo>,
    val product: Int,
    val content: String
)
