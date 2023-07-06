package com.cibertec.app.networking

import com.cibertec.app.model.*
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

object ApiVenta {

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
        @GET("api/VentaAPI")
        suspend fun getAllVentas() : Response<List<Venta>>
        //suspend fun getAllPets() : Response<List<Pet>>

        @POST("api/VentaAPI")
        suspend fun saveVenta(@Body request: RegisterVentaRequest) :Response<ResultApi>
        //suspend fun savePet(@Body request: RegisterPetRequest) :Response<ResultApi>

        @GET("api/VentaAPI")
        suspend fun findVenta(@Query("id") idventa: String) :Response<List<RegisterVentaRequest>>

        @PUT("api/VentaAPI")
        suspend fun updateVenta(@Body request: RegisterVentaRequest) :Response<ResultApi>

        @DELETE("api/VentaAPI/{idventa}")
        suspend fun deleteDulces(@Path("idventa") idventa: String): Response<ResultApi>

        /*@GET("api/FavoritoAPI")
        suspend fun getPetById(@Query("codigo") code:Int) :Pet*/
        //@DELETE("api/Mascota/{codigo}")
        //suspend fun deletePetPath(@Path("codigo") code: Int)
    }

    //3. Devolver la instancia de retrofit

    fun build() : RemoteService{
        return builder.build().create(RemoteService::class.java)
    }

}