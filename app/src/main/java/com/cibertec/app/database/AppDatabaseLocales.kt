package com.cibertec.app.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.cibertec.app.model.Locales


@Database(entities = [Locales::class], version = 1)
abstract class AppDatabaseLocales: RoomDatabase() {

    //Definir los datos del proyecto
    abstract fun localDao(): LocalesDAO

    companion object{
        var appDatabase: AppDatabaseLocales? = null
        fun getInstance(context: Context): AppDatabaseLocales {
            //Si la bd de datos existe y devulevo la instancia
            //si no esta creada, la creo y devuelvo la instancia
            if (appDatabase == null) {
                //crear la bd
                appDatabase = Room.databaseBuilder(
                    context,
                    AppDatabaseLocales::class.java,
                    "db_global_movie2",
                ).allowMainThreadQueries().build()
            }
            return appDatabase!!
        }
    }
}