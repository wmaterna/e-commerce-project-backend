package com.example.entities.product

import com.example.entities.opinion.Opinion

data class ProductDetails(
    val id: Int,
    var name: String,
    var description: String,
    var price: Double,
    var recommendations: String,
    var url: String,
    var subcategoryId: Int,
    var opinions: List<Opinion>
    )
