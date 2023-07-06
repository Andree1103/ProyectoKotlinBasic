package com.cibertec.app.model

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "table_locales")
data class Locales (

    @PrimaryKey(autoGenerate = true)
    @NonNull
    val id:Int,

    @ColumnInfo(name = "Nombre_del_Local")
    val nomLocal:String,

    @ColumnInfo(name = "Descripcion_del_Local")
    val descripLocal:String,

    @ColumnInfo(name = "Correo_del_Local")
    val correoLocal:String,

    @ColumnInfo(name = "Numero_de_local")
    val telefonoLocal:String,

    @ColumnInfo(name = "Distrito")
    val distritLocal:String,

    @ColumnInfo(name = "Tipo_de_Local")
    val TipoLocal:String,

    @ColumnInfo(name = "Requisitos")
    val Requisitos:String

)