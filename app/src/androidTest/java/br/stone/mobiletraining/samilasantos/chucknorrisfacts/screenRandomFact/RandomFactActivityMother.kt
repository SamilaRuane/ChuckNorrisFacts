package br.stone.mobiletraining.samilasantos.chucknorrisfacts.screenRandomFact

object RandomFactActivityMother {
    const val timeoutFeedback: String = "Tempo excedido ao tentar recuperar as informações."
    const val unavailableProviderFeedback: String =
        "Ocorreu um erro ao conectar com o servidor. Tente novamente mais tarde."
    const val unexpectedDataFeedback: String =
        "Ocorreu um erro ao recuperar as informações. Tente novamente mais tarde."
    const val fact =
        "Chuck Norris ride into town on Friday, stayed three Nights, the rode out again on Friday. On a horse named Steve."

    val successBody = """
        {
           "category":null,
           "icon_url":"https:\/\/assets.chucknorris.host\/img\/avatar\/chuck-norris.png",
           "id":"0PlGg24YSX--haND5nj4Tw",
           "url":"https:\/\/api.chucknorris.io\/jokes\/0PlGg24YSX--haND5nj4Tw",
           "value":"Chuck Norris ride into town on Friday, stayed three Nights, the rode out again on Friday. On a horse named Steve."
        }
    """.trimIndent()

    val malFormedBody = """
        {[jdskjdsf
           "category":null,
           "icon_url":"https:\/\/assets.chucknorris.host\/img\/avatar\/chuck-norris.png",
           "id":"0PlGg24YSX--haND5nj4Tw",
           "url":"https:\/\/api.chucknorris.io\/jokes\/0PlGg24YSX--haND5nj4Tw",
           "value":"Chuck Norris ride into town on Friday, stayed three Nights, the rode out again on Friday. On a horse named Steve."
        }
    """.trimIndent()
}