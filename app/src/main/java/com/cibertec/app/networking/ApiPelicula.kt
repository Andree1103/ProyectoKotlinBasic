package com.cibertec.app.networking

import com.cibertec.app.model.*
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

object ApiPelicula {

//1. Configurar un retrofit
    //Metodo completo url o endpoint : http://cibertec-001-site1.etempurl.com/api/Mascota
    //url base = http//cibertec-001-site1.etemurl.com/
    //metodo api = api/Mascota

    private val builder : Retrofit.Builder =  Retrofit.Builder()
        .baseUrl("http://apiemovies-001-site1.gtempurl.com/")
        //.baseUrl("http://cibertec-001-site1.etempurl.com/")
        .addConverterFactory(GsonConverterFactory.create())

    //2. Configurar los metodos
    interface RemoteService{
        @GET("api/PeliculasAPI")
        suspend fun getAllPeliculas() : Response<List<Pelicula>>

        @POST("api/PeliculasAPI")
        suspend fun savePeli(@Body request: RegisterPeliculaRequets) :Response<ResultApi>

        @PUT("api/PeliculasAPI")
        suspend fun updatePeli(@Body request: RegisterPeliculaRequets) :Response<ResultApi>

        @DELETE("api/PeliculasAPI/{idpelicula}")
        suspend fun deletePeli(@Path("idpelicula") idpelicula: String): Response<ResultApi>

    }

    //3. Devolver la instancia de retrofit

    fun build() : RemoteService{
        return builder.build().create(RemoteService::class.java)
    }
}