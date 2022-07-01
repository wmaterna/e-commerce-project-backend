package com.example.database

import com.example.database.dbEntities.DBCategoryTable
import com.example.database.dbEntities.DBSubcategoryTable
import com.example.entities.category.Category
import com.example.entities.category.SubcategoriesForCategories
import org.ktorm.dsl.*

class ManagerCategory {
    private val database = DatabaseManager()
    private val ktormDatabase = database.dataBaseConnection()

    fun getCategories() : List<Category> {
        val categories = ktormDatabase.from(DBCategoryTable)
            .select()
            .map {
                Category(
                    it[DBCategoryTable.id]!!,
                    it[DBCategoryTable.name]!!,
                    ktormDatabase.from(DBSubcategoryTable)
                        .select(DBSubcategoryTable.id, DBSubcategoryTable.name)
                        .where{ DBSubcategoryTable.category eq it[DBCategoryTable.id]!!}
                        .map{
                            SubcategoriesForCategories(
                                it[DBSubcategoryTable.id]!!,
                                it[DBSubcategoryTable.name]!!
                            )
                        }
                )
            }
        return categories
    }


    fun getCategory(id: Int) : List<Category> {
        val category = ktormDatabase.from(DBCategoryTable)
            .select()
            .where{ DBCategoryTable.id eq id}
            .map {
                Category(
                    it[DBCategoryTable.id]!!,
                    it[DBCategoryTable.name]!!,
                    ktormDatabase.from(DBSubcategoryTable)
                        .select(DBSubcategoryTable.id, DBSubcategoryTable.name)
                        .where{ DBSubcategoryTable.category eq it[DBCategoryTable.id]!!}
                        .map{
                            SubcategoriesForCategories(
                                it[DBSubcategoryTable.id]!!,
                                it[DBSubcategoryTable.name]!!
                            )
                        }
                )
            }
        return category
    }
}