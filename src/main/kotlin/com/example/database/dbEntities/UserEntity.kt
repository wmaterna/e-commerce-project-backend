package com.example.database.dbEntities

import org.ktorm.entity.Entity
import org.ktorm.schema.*

object DBUserTable: Table<DBUserEntity>("user") {

    val id = int("user_id").primaryKey().bindTo { it.id }
    val name = varchar("name").bindTo { it.name }
    val city = varchar("city").bindTo { it.city }
    val street = varchar("street").bindTo { it.street }
    val apartmentNo = varchar("apartment_no").bindTo { it.apartmentNo }
    val postCode = varchar("post_code").bindTo { it.postCode }
    var oauthId = varchar("oauth_id").bindTo { it.oauthId }

}

interface DBUserEntity: Entity<DBUserEntity> {
    companion object: Entity.Factory<DBUserEntity>()
    val id: Int
    val name: String
    val city: String
    val street: String
    val apartmentNo: String
    val postCode: String
    val oauthId: String
}