package br.stone.mobiletraining.samilasantos.domain.randomFact.uc

sealed class RandomFactExceptions : Throwable() {
    object FactNotFound : RandomFactExceptions()
}