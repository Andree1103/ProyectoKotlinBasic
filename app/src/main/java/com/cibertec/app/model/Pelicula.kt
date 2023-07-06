package com.cibertec.app.model

import com.google.gson.annotations.SerializedName
data class Pelicula (

    @SerializedName("idpelicula")
    val idpelicula:String,

    @SerializedName("titulo")
    val titulo:String,

    @SerializedName("descripcion")
    val descripcion:String,

    @SerializedName("duracion")
    val duracion:String,

    @SerializedName("genero")
    val genero:String,

    @SerializedName("precio")
    val precio:String,

    @SerializedName("stock")
    val stock:String,

    @SerializedName("publico")
    val publico:String,

    @SerializedName("eliminado")
    val eliminado:String
)
