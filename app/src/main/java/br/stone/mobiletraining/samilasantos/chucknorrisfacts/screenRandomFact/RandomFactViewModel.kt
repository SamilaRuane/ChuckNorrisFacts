package br.stone.mobiletraining.samilasantos.chucknorrisfacts.screenRandomFact

import br.stone.mobiletraining.samilasantos.domain.randomFact.RandomFactResult
import br.stone.mobiletraining.samilasantos.domain.randomFact.uc.GetRandomFact
import br.stone.mobiletraining.samilasantos.domain.shareFact.FactSharer
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import java.util.concurrent.TimeUnit

class RandomFactViewModel(
    private val getRandomFact: GetRandomFact,
    private val factSharer: FactSharer
) {

    private val updateIntentObservable = PublishSubject.create<Unit>()

    fun handleUpdateClick() {
        updateIntentObservable.onNext(Unit)
    }

    fun observeState(): Observable<RandomFactContract.ViewState> = updateIntentObservable
        .throttleFirst(500, TimeUnit.MILLISECONDS)
        .switchMap {
            getRandomFact.execute()
                .map {
                    when (it) {
                        is RandomFactResult.Success -> RandomFactContract.ViewState.Success(it.fact.description)
                        is RandomFactResult.Error -> RandomFactContract.ViewState.Error(it.error)
                    }
                }
                .toObservable()
                .startWith(RandomFactContract.ViewState.Loading)
        }

    fun handleWithShareClick(fact: String) {
        factSharer.share(fact)
    }
}