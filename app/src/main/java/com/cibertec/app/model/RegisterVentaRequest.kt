package com.cibertec.app.model

data class RegisterVentaRequest(
    val idventa : String,
    val nombrecliente : String,
    val correonumero : String,
    val direccion : String,
    val dni : String,
    val nombrepeli : String,
    val cantidad : String,
    val tipopago : String,
    val terminos : String
)
