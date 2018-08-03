package br.stone.mobiletraining.samilasantos.data.service.common

import br.stone.mobiletraining.samilasantos.domain.common.Fact
import br.stone.mobiletraining.samilasantos.domain.randomFact.RandomFactRepository
import com.google.gson.annotations.SerializedName
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.http.GET

class RetrofitFactRepository(retrofit: Retrofit) : RandomFactRepository {
    private val api = retrofit.create(ChuckNorrisApi::class.java)

    override fun getFact(): Single<Fact> = api.getRandomFact()
        .subscribeOn(Schedulers.io())
        .compose(HandleConnectionErrors())
        .compose(HandleParsingErrors())
        .compose(HandleHttpErrors())
        .map { fact ->
            Fact(
                fact.id,
                fact.value,
                fact.url,
                fact.category?.first() ?: "Uncategorized"
            )
        }
}

private interface ChuckNorrisApi {

    @GET("jokes/random")
    fun getRandomFact(): Single<br.stone.mobiletraining.samilasantos.data.service.common.Fact>
}

private data class Fact(
    val category: List<String>?,
    @SerializedName("icon_url")
    val iconUrl: String,
    val id: String,
    val url: String,
    val value: String
)