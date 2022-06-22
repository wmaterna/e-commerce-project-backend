package com.example.database.dbEntities

import org.ktorm.entity.Entity
import org.ktorm.schema.*

object DBProductTable: Table<DBProductEntity>("product") {

    val id = int("id").primaryKey().bindTo { it.id }
    val name = varchar("name").bindTo { it.name }
    val description = varchar("description").bindTo { it.description }
    val price = long("price").bindTo { it.price }
    val recommendations = varchar("recommendations").bindTo { it.recommendations }
    val url = varchar("url").bindTo { it.url }
    val subcategory = int("subcategor_id").references(DBSubcategoryTable) { it.subcategory }
}

interface DBProductEntity: Entity<DBProductEntity> {
    companion object: Entity.Factory<DBProductEntity>()
    val id: Int
    val name: String
    val description: String
    val price: Long
    val recommendations: String
    val url: String
    val subcategory: DBSubcategorieEntity
    val opinions get() = listOf(DBOpinionEntity)
}