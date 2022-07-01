package com.example.entities.order


data class OrderDraft(
    val id: Int,
    var date: String,
    var address: String,
    var price: Double,
)
