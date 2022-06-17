package com.example.repository.productRepository

import com.example.database.dbEntities.DBSubcategorieEntity
import com.example.entities.subcategory.Subcategory

interface SubcategoryRepository {
    fun getAllSubcategories(): List<Subcategory>

//    fun getSubcateogry(id: Int): Subcategory?
}