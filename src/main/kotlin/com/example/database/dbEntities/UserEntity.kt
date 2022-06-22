package com.example.database.dbEntities

import org.ktorm.entity.Entity
import org.ktorm.schema.*

object DBUserTable: Table<DBUserEntity>("user") {

    val id = int("user_id").primaryKey().bindTo { it.id }
    val name = varchar("name").bindTo { it.name }
    val city = varchar("city").bindTo { it.city }
    val street = varchar("street").bindTo { it.street }
    val apartment_no = varchar("apartment_no").bindTo { it.apartment_no }
    val post_code = varchar("post_code").bindTo { it.post_code }

}

interface DBUserEntity: Entity<DBUserEntity> {
    companion object: Entity.Factory<DBUserEntity>()
    val id: Int
    val name: String
    val city: String
    val street: String
    val apartment_no: String
    val post_code: String
}