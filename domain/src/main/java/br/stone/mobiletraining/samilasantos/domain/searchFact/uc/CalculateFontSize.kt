package br.stone.mobiletraining.samilasantos.domain.searchFact.uc

import io.reactivex.Single

class CalculateFontSize {
    fun using(descritionSize: Int): Single<Int> =
        if (descritionSize > 50) Single.just(14) else Single.just(16)
}