package br.stone.mobiletraining.samilasantos.domain.shareFact

class FactSharer(private val shareHandler: ShareHandler) {
    fun share(fact: String) {
        shareHandler.share(fact)
    }
}