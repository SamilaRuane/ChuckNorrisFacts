package br.stone.mobiletraining.samilasantos.data

import br.stone.mobiletraining.samilasantos.domain.common.Fact
import br.stone.mobiletraining.samilasantos.domain.randomFact.RandomFactRepository
import io.reactivex.Single
import java.util.concurrent.TimeUnit

class RepositoryMock : RandomFactRepository {
    override fun getFact(): Single<Fact> =
        Single.timer(2, TimeUnit.SECONDS)
            .map {
                Fact(id = "545645",
                    description = "Once a player found Chuck Norris on super smash bros for 3DS. He was instantly beaten from Chuck Norris when the Challenger round started.",
                    url = "randomfact.com.br")
            }
}