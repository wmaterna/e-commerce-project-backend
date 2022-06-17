package com.example.entities.subcategory

import com.example.entities.product.Product

data class SubcategoryDraft(
    var name: String,
    var products: List<Product>
)
