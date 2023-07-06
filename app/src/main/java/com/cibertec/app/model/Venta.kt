package com.cibertec.app.model


import com.google.gson.annotations.SerializedName


data class Venta (

    @SerializedName("idventa")
    val idventa:String,

    @SerializedName("nombrecliente")
    val nombrecliente:String,

    @SerializedName("correonumero")
    val correonumero:String,

    @SerializedName("direccion")
    val direccion:String,

    @SerializedName("dni")
    val dni:String,

    @SerializedName("nombrepeli")
    val nombrepeli:String,

    @SerializedName("cantidad")
    val cantidad:String,

    @SerializedName("tipopago")
    val tipopago:String,

    @SerializedName("terminos")
    val terminos:String,

    @SerializedName("eliminado")
    val eliminado:String


)