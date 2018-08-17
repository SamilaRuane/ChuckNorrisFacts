package br.stone.mobiletraining.samilasantos.domain.shareFact

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever

object FactSharerMother {
    fun withASuccessScenario(func: (ShareHandler) -> Unit) {
        val handler = mock<ShareHandler>()
        whenever(handler.share("something")).thenReturn(true)
        func(handler)
    }

    fun withAnErrorScenario(func: (ShareHandler) -> Unit) {
        val handler = mock<ShareHandler>()
        whenever(handler.share("something")).thenReturn(false)
        func(handler)
    }
}