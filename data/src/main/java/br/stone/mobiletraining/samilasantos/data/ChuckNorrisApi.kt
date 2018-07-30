package br.stone.mobiletraining.samilasantos.data

import retrofit2.http.GET

interface ChuckNorrisApi {

    @GET("https://api.chucknorris.io/jokes/random")
    fun getRandomFact ()
}