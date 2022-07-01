package com.example.database

import com.example.database.dbEntities.DBOpinionTable
import com.example.database.dbEntities.DBUserTable
import com.example.entities.opinion.Opinion
import com.example.entities.opinion.OpinionDraft
import com.example.entities.user.UserOpinionInfo
import org.ktorm.dsl.*

class ManagerOpinion {
    private val database = DatabaseManager()
    private val ktormDatabase = database.dataBaseConnection()

    fun addOpinion(newOpinion: OpinionDraft, oauthId: String): Opinion {
        var user = ktormDatabase.from(DBUserTable)
            .select()
            .where { DBUserTable.oauthId eq oauthId}
            .map {
                UserOpinionInfo(it[DBUserTable.id]!!, it[DBUserTable.name])
            }

        val insertedId = ktormDatabase.insertAndGenerateKey(DBOpinionTable) {
            set(DBOpinionTable.user, user[0].id)
            set(DBOpinionTable.product_id, newOpinion.product)
            set(DBOpinionTable.content, newOpinion.content)
        } as Int



        return Opinion(insertedId, user, newOpinion.product, newOpinion.content)
    }

    fun removeOpinion(id: Int): Boolean{
        val deleteRow = ktormDatabase.delete(DBOpinionTable){
            it.id eq id
        }
        return deleteRow > 0
    }
}