package br.stone.mobiletraining.samilasantos.data.service.searchFact

import br.stone.mobiletraining.samilasantos.domain.common.Fact
import br.stone.mobiletraining.samilasantos.domain.searchFact.SearchFactRepository
import io.reactivex.Single
import java.util.concurrent.TimeUnit

class MockSearchFactRepository : SearchFactRepository {
    private val mockedFacts =
        arrayListOf(
            Fact(
                id = "AFEG7878DD",
                description = "Chuck Norris is Extreme Go Horse Father",
                url = "", category = "Uncategorized"
            ),
            Fact(
                id = "AFEG7878DD",
                description = "Chuck Norris is Extreme Go Horse Father",
                url = "", category = "Morango"
            ),
            Fact(
                id = "AFEG7878DD",
                description = "Chuck Norris is Extreme Go Horse Father",
                url = "", category = "Banana"
            ),
            Fact(
                id = "AFEG7878DD",
                description = "Chuck Norris is Extreme Go Horse Father",
                url = "", category = "Friends"
            ),
            Fact(
                id = "AFEG7878DD",
                description = "Chuck Norris is Extreme Go Horse Father " +
                    "Chuck Norris is Extreme Go Horse Father Chuck Norris is Extreme Go Horse Father " +
                    "Chuck Norris is Extreme Go Horse Father Chuck Norris is Extreme Go Horse Father",
                url = "", category = "Uncategorized"
            )
        )

    override fun getFactsThatContains(query: String): Single<List<Fact>> =
        Single.timer(1, TimeUnit.SECONDS)
            .map { mockedFacts }
}