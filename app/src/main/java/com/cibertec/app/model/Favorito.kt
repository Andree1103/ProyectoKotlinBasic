package com.cibertec.app.model

import com.google.gson.annotations.SerializedName

data class Favorito (


    @SerializedName("idfav")
    val idfav:String,

    @SerializedName("nombre_clie")
    val nombre_clie:String,

    @SerializedName("correo")
    val correo:String,

    @SerializedName("nombre_peli")
    val nombre_peli:String,

    @SerializedName("reseña")
    val resenia:String,

    @SerializedName("terminos")
    val terminos:String,

    @SerializedName("eliminado")
    val eliminado:String

)