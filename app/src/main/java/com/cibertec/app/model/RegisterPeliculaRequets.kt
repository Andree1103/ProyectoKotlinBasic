package com.cibertec.app.model


data class RegisterPeliculaRequets (
    val idpelicula:String,
    val titulo:String,
    val descripcion:String,
    val duracion:String,
    val genero:String,
    val precio:String,
    val stock:String,
    val publico:String
)