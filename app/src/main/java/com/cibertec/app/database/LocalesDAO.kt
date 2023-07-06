package com.cibertec.app.database

import androidx.room.*
import com.cibertec.app.model.Locales

@Dao
interface LocalesDAO {
    //LOCALES
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(locales: Locales)

    @Update
    fun update(locales: Locales)

    @Delete
    fun delete (locales: Locales)

    @Query("SELECT * FROM  table_locales order by id desc")
    fun getAllLocales() : List<Locales>
}