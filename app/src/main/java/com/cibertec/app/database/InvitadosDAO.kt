package com.cibertec.app.database

import androidx.room.*
import com.cibertec.app.model.Invitados
import com.cibertec.app.model.Locales

@Dao
interface InvitadosDAO {
    //LOCALES
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(invitados: Invitados)

    @Update
    fun update(invitados: Invitados)

    @Delete
    fun delete (invitados: Invitados)

    @Query("SELECT * FROM  table_invitados order by id desc")
    fun getAllInvitados() : List<Invitados>
}