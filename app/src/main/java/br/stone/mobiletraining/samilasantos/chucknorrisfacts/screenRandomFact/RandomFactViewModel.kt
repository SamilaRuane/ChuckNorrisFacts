package br.stone.mobiletraining.samilasantos.chucknorrisfacts.screenRandomFact

import br.stone.mobiletraining.samilasantos.domain.randomFact.RandomFactRepository
import br.stone.mobiletraining.samilasantos.domain.randomFact.RandomFactResult
import br.stone.mobiletraining.samilasantos.domain.randomFact.uc.GetRandomFact
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

class RandomFactViewModel(private val repository: RandomFactRepository) {

    private val updateIntentObservable = PublishSubject.create<Unit>()

    fun handleUpdateClick() {
        updateIntentObservable.onNext(Unit)
    }

    fun observeState(): Observable<RandomFactContract.ViewState> = updateIntentObservable.map {
        GetRandomFact(repository).execute()
    }
        .flatMap {
            it.map {
                when (it) {
                    is RandomFactResult.Success -> RandomFactContract.ViewState.Success(it.fact.description)
                    is RandomFactResult.Error -> RandomFactContract.ViewState.Error(it.error)
                }
            }.toObservable().startWith(Observable.just(RandomFactContract.ViewState.Loading))
        }
}