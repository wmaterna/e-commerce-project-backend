package com.example.entities.product


data class Product(
    val id: Int,
    var name: String,
    var description: String,
    var price: Double,
    var recommendations: String,
    var url: String,
    var subcategoryId: Int,
//    var opinions: List<Opinion>
)
