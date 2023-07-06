package com.cibertec.app.model

import com.google.gson.annotations.SerializedName

data class RegisterDulcesRequets (


    @SerializedName("iddulces")
    val iddulces:String,

    @SerializedName("nombrecliente")
    val nombrecliente:String,

    @SerializedName("producto")
    val producto:String,

    @SerializedName("cantidad")
    val cantidad:String,

    @SerializedName("tipopago")
    val tipopago:String,




)