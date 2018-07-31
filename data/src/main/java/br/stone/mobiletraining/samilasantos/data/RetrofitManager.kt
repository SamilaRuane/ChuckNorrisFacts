package br.stone.mobiletraining.samilasantos.data

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitManager {
    fun chuckNorrisFactsService (): ChuckNorrisApi {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        val httpClient =
            OkHttpClient.Builder()
                .addInterceptor(logging)
                .build()

        val service = Retrofit
            .Builder()
            .baseUrl("https://api.chucknorris.io/")
            .client(httpClient)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return service.create(ChuckNorrisApi :: class.java)

    }
}