package com.example.database.dbEntities

import org.ktorm.entity.Entity
import org.ktorm.schema.*


object DBCategoryTable: Table<DBCategoryEntity>("category") {

    val id = int("id").primaryKey().bindTo { it.id }
    val name = varchar("name").bindTo { it.name }

}

interface DBCategoryEntity: Entity<DBCategoryEntity> {
    companion object: Entity.Factory<DBCategoryEntity>()
    val id: Int
    val name: String
    val subcategories get() = listOf(DBSubcategorieEntity)
}