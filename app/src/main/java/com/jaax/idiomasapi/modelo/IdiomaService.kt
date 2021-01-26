package com.jaax.idiomasapi.modelo

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface IdiomaService {
    @GET( RetrofitClient.ENDPOINT_IDIOMAS )
    fun getAllIdiomas(): Call<ArrayList<Idioma>>

    @POST( RetrofitClient.ENDPOINT_IDIOMAS )
    fun post( @Body idioma: Idioma ): Call<Idioma>

    fun putByCode( code: String )
    fun deleteByCode( code: String )
}