package com.example.entities.product

import com.example.entities.opinion.Opinion

data class ProductDraft(
    var name: String,
    var description: String,
    var price: Long,
    var recommendations: String,
//    var opinions: List<Opinion>
)
