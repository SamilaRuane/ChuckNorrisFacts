package br.stone.mobiletraining.samilasantos.data.service.common

import io.reactivex.Single
import retrofit2.http.GET

interface ChuckNorrisApi {

    @GET("jokes/random")
    fun getRandomFact(): Single<Fact>
}