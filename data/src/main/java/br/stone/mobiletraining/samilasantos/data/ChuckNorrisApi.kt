package br.stone.mobiletraining.samilasantos.data

import io.reactivex.Observable
import retrofit2.http.GET

interface ChuckNorrisApi {

    @GET("jokes/random")
    fun getRandomFact(): Observable<Fact>
}