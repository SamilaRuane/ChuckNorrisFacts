package br.stone.mobiletraining.samilasantos.data.service.common

import br.stone.mobiletraining.samilasantos.data.service.common.mappers.RetrofitMappers
import br.stone.mobiletraining.samilasantos.data.service.randomFact.HandleRandomFactHttpExceptions
import br.stone.mobiletraining.samilasantos.data.service.searchFact.HandleEmptyFactsListResult
import br.stone.mobiletraining.samilasantos.data.service.searchFact.HandleSearchFactHttpExceptions
import br.stone.mobiletraining.samilasantos.data.service.searchFact.SearchResponse
import br.stone.mobiletraining.samilasantos.domain.common.Fact
import br.stone.mobiletraining.samilasantos.domain.randomFact.RandomFactRepository
import br.stone.mobiletraining.samilasantos.domain.searchFact.SearchFactRepository
import com.google.gson.annotations.SerializedName
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Query

class RetrofitFactRepository(retrofit: Retrofit) : RandomFactRepository, SearchFactRepository {
    private val api = retrofit.create(ChuckNorrisApi::class.java)

    override fun getFact(): Single<Fact> = api.getRandomFact()
        .subscribeOn(Schedulers.io())
        .compose(
            HandleConnectionExceptions(
                RetrofitMappers.mapToConnectionTimeout,
                RetrofitMappers.mapToNoInternetException
            )
        )
        .compose(HandleParsingExceptions(RetrofitMappers.mapToParsingException))
        .map { fact ->
            Fact(
                fact.id,
                fact.value,
                fact.url,
                fact.category?.first() ?: "Uncategorized"
            )
        }
        .compose(HandleRandomFactHttpExceptions())

    override fun getFactsThatContains(query: String): Single<List<Fact>> =
        api.getFactBy(query)
            .subscribeOn(Schedulers.io())
            .compose(
                HandleConnectionExceptions(
                    RetrofitMappers.mapToConnectionTimeout,
                    RetrofitMappers.mapToNoInternetException
                )
            )
            .compose(HandleParsingExceptions(RetrofitMappers.mapToParsingException))
            .map { it.result }
            .map { it }
            .map { facts ->
                facts.transform()
            }
            .compose(HandleEmptyFactsListResult())
            .compose(HandleSearchFactHttpExceptions())

    private fun List<br.stone.mobiletraining.samilasantos.data.service.common.Fact>.transform(): List<Fact> =
        map {
            Fact(
                id = it.id,
                description = it.value,
                url = it.url,
                category = it.category?.first() ?: "Uncategorized"
            )
        }
}

private interface ChuckNorrisApi {

    @GET("jokes/random")
    fun getRandomFact(): Single<br.stone.mobiletraining.samilasantos.data.service.common.Fact>

    @GET("/jokes/search")
    fun getFactBy(@Query(value = "query") query: String): Single<SearchResponse>
}

internal data class Fact(
    val category: List<String>?,
    @SerializedName("icon_url")
    val iconUrl: String,
    val id: String,
    val url: String,
    val value: String
)