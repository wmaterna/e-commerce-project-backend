package com.example.entities.category


data class Category(
    val id: Int,
    var name: String,
    var subcategory: List<SubcategoriesForCategories>
)
