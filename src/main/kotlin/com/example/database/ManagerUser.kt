package com.example.database

import com.example.database.dbEntities.DBUserEntity
import com.example.database.dbEntities.DBUserTable
import com.example.entities.user.User
import org.ktorm.dsl.*

class ManagerUser {
    private val database = DatabaseManager()
    private val ktormDatabase = database.dataBaseConnection()

    fun getUserInfo(id: Int): List<User> {
        return ktormDatabase.from(DBUserTable)
            .select()
            .where { DBUserTable.id eq id }
            .map{
                User(it[DBUserTable.id]!!, it[DBUserTable.name]!!, it[DBUserTable.city]!!, it[DBUserTable.street]!!,
                    it[DBUserTable.apartment_no]!!, it[DBUserTable.post_code]!!)
            }
    }
}
