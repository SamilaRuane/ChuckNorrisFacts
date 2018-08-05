package br.stone.mobiletraining.samilasantos.domain.randomFact

import br.stone.mobiletraining.samilasantos.domain.common.Fact

sealed class RandomFactResult {
    data class Success(val fact: Fact) : RandomFactResult()
    data class Error(val error: Throwable) : RandomFactResult()
}