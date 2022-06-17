package com.example.repository.productRepository

import com.example.database.DatabaseManagerSubcategories
import com.example.entities.product.Product
import com.example.entities.subcategory.Subcategory

class DBSubcategoriesRepository : SubcategoryRepository {

    private val database = DatabaseManagerSubcategories()
    override fun getAllSubcategories(): List<Subcategory> {
        val asd = database.getAllSubcategories().map{ Subcategory(
            it.id,
            it.name,
            it.products
        )
        }
        return asd;

    }

}