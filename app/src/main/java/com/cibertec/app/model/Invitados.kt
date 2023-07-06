package com.cibertec.app.model

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "table_invitados")
data class Invitados(

    @PrimaryKey(autoGenerate = true)
    @NonNull
    val id:Int,

    @ColumnInfo(name = "Nombre del Invitado")
    val nomUser:String,

    @ColumnInfo(name = "Correo del Invitado")
    val Correo:String


)
