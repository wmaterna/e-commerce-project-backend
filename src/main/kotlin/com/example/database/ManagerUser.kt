package com.example.database

import com.example.database.dbEntities.DBUserTable
import com.example.entities.user.User
import com.example.entities.user.UserDraft
import org.ktorm.dsl.*

class ManagerUser {
    private val database = DatabaseManager()
    private val ktormDatabase = database.dataBaseConnection()

    fun getUserInfo(id: String): List<User> {
        return ktormDatabase.from(DBUserTable)
            .select()
            .where { DBUserTable.oauth_id eq id }
            .map{
                User(it[DBUserTable.id]!!, it[DBUserTable.name], it[DBUserTable.city], it[DBUserTable.street],
                    it[DBUserTable.apartment_no], it[DBUserTable.post_code], it[DBUserTable.oauth_id])
            }
    }

    fun updateUserInfo(userId : String, updatedUser: UserDraft): Boolean{
        val updatedRows = ktormDatabase.update(DBUserTable){
            set(DBUserTable.name, updatedUser.name)
            set(DBUserTable.city, updatedUser.city)
            set(DBUserTable.apartment_no, updatedUser.apartment_no)
            set(DBUserTable.post_code, updatedUser.post_code)
            set(DBUserTable.street, updatedUser.street)
            where {
                it.oauth_id eq userId
            }
        }
        return updatedRows > 0
    }

    fun checkIfUserExists(oauthId: String):Boolean{
        val user = ktormDatabase.from(DBUserTable).select()
            .where{
                DBUserTable.oauth_id eq oauthId
            }
            .map{
                User(it[DBUserTable.id]!!, it[DBUserTable.name], null, null,
                    null, null, it[DBUserTable.oauth_id])
            }
        return user.size !== 0
    }
    fun createUser(id: String, name: String?): Int? {
        var userExists = checkIfUserExists(id)
        if (userExists) {
            var userId = ktormDatabase.from(DBUserTable).select(DBUserTable.id)
                .where {
                    DBUserTable.oauth_id eq id
                }
                .map {
                    User(
                        it[DBUserTable.id]!!, it[DBUserTable.name], null, null,
                        null, null, it[DBUserTable.oauth_id]
                    )
                }
        } else {
            var userId = ktormDatabase.insertAndGenerateKey(DBUserTable) {
                set(DBUserTable.name, name)
                set(DBUserTable.oauth_id, id)
            } as Int
            return userId
        }
        return null
  }
}
