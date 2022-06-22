package com.example.database.dbEntities

import org.ktorm.entity.Entity
import org.ktorm.schema.*

object DBOpinionTable: Table<DBOpinionEntity>("opinion") {

    val id = int("id").primaryKey().bindTo { it.id }
    val user = int("user").references(DBUserTable) { it.user }
    val product_id = int("product_id").references(DBProductTable) { it.product_id }
    val content = varchar("content").bindTo { it.content }


}

interface DBOpinionEntity: Entity<DBOpinionEntity> {
    companion object: Entity.Factory<DBOpinionEntity>()
    val id: Int
    val user: DBUserEntity
    val product_id: DBProductEntity
    val content: String
}