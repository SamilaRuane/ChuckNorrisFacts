package br.stone.mobiletraining.samilasantos.domain.randomFact

sealed class RandomFactExceptions : Throwable() {
    object FactNotFound : RandomFactExceptions()
}