package com.example.entities.category

import com.example.entities.subcategory.Subcategory

data class Category(
    val id: Int,
    var name: String,
    var subcategory: List<Subcategory>
)
