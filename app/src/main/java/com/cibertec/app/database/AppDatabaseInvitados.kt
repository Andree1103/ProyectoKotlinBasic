package com.cibertec.app.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.cibertec.app.model.Invitados


@Database(entities = [Invitados::class], version = 1)
abstract class AppDatabaseInvitados: RoomDatabase() {

    //Definir los datos del proyecto
    abstract fun invitadosDAO(): InvitadosDAO


    companion object{
        var appDatabase: AppDatabaseInvitados? = null
        fun getInstance(context: Context): AppDatabaseInvitados {
            //Si la bd de datos existe y devulevo la instancia
            //si no esta creada, la creo y devuelvo la instancia
            if (appDatabase == null) {
                //crear la bd
                appDatabase = Room.databaseBuilder(
                    context,
                    AppDatabaseInvitados::class.java,
                    "db_global_invitados",
                ).build()
            }
            return appDatabase!!
        }
    }
}