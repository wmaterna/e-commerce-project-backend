package com.example.entities.category

import com.example.entities.subcategory.Subcategory

data class CategoryDraft(
    var name: String,
    var subcategory: List<Subcategory>
)
