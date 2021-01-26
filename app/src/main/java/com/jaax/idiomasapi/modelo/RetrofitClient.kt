package com.jaax.idiomasapi.modelo

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {

    companion object {
        const val BASE_URL = "http://192.168.1.50:8080/api/v1/"
        const val ENDPOINT_IDIOMAS = "idiomas"
    }

    private fun initRetrofit(): Retrofit {
        return Retrofit
            .Builder()
            .baseUrl( BASE_URL )
            .addConverterFactory( GsonConverterFactory.create() )
            .build()
    }

    fun getIdiomaService(): IdiomaService {
        return initRetrofit().create( IdiomaService::class.java )
    }
}