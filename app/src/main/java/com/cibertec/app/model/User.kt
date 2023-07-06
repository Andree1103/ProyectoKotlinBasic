package com.cibertec.app.model

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "table_user")
data class User(

    @PrimaryKey(autoGenerate = true)
    @NonNull
    val codigo:Int,

    @ColumnInfo(name = "Nombre_Usuario")
    val nomUser:String,

    @ColumnInfo(name = "Apellidos_Usuario")
    val apeUser:String,

    @ColumnInfo(name = "Email_Usuario")
    val emailUser:String,

    @ColumnInfo(name = "Celular_Usuario")
    val phoneUser:String,

    @ColumnInfo(name = "Password_Usuario")
    val passwordUser:String,

    @ColumnInfo(name = "Documento_Usuario")
    val docUser:String,

    @ColumnInfo(name = "NRO_Documento_Usuario")
    val nrodocUser:String,

    @ColumnInfo(name = "Genero_Usuario")
    val genUser:String


)
