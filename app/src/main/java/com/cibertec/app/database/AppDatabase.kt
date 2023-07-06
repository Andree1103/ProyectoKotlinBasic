package com.cibertec.app.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.cibertec.app.model.Locales
import com.cibertec.app.model.User

@Database(entities = [User::class], version = 1)
abstract class AppDatabase: RoomDatabase() {

    //Definir los datos del proyecto
    abstract fun userDao(): UserDao


    companion object{
        var appDatabase: AppDatabase? = null
        fun getInstance(context: Context): AppDatabase {
            //Si la bd de datos existe y devulevo la instancia
            //si no esta creada, la creo y devuelvo la instancia
            if (appDatabase == null) {
                //crear la bd
                appDatabase = Room.databaseBuilder(
                    context,
                    AppDatabase::class.java,
                    "db_global_movie",
                ).build()
            }
            return appDatabase!!
        }
    }
}