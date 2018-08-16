package br.stone.mobiletraining.samilasantos.domain.shareFact

import org.junit.Assert
import org.junit.Test

class FactSharerTest {
    @Test
    fun `given an success scenario should return true`() {
        FactSharerMother.withASuccessScenario { handler ->
            Assert.assertTrue(handler.share("something"))
        }
    }

    @Test
    fun `given an error scenario should return false`() {
        FactSharerMother.withAnErrorScenario { handler ->
            Assert.assertFalse(handler.share("something"))
        }
    }
}