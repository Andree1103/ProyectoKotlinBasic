package com.cibertec.app.networking

import com.cibertec.app.model.*
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

object ApiFavorito {

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
        @GET("api/FavoritoAPI")
        suspend fun getAllFavoritos() : Response<List<Favorito>>

        @POST("api/FavoritoAPI")
        suspend fun saveFav(@Body request: RegisterFavoritoRequets) :Response<ResultApi>

        @PUT("api/FavoritoAPI")
        suspend fun updateFav(@Body request: RegisterFavoritoRequets) :Response<ResultApi>

        @DELETE("api/FavoritoAPI/{idfav}")
        suspend fun deleteFav(@Path("idfav") idfav: String): Response<ResultApi>

    }



    fun build() : RemoteService{
        return builder.build().create(RemoteService::class.java)
    }
}