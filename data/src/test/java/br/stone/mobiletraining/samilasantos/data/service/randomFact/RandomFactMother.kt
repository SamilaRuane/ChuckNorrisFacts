package br.stone.mobiletraining.samilasantos.data.service.randomFact

import br.stone.mobiletraining.samilasantos.domain.common.Fact

object RandomFactMother {

    val successBodyObject = Fact(
        id = "0PlGg24YSX--haND5nj4Tw",
        description = "Chuck Norris ride into town on Friday, stayed three Nights, the rode out again on Friday. On a horse named Steve.",
        url = "https://api.chucknorris.io/jokes/0PlGg24YSX--haND5nj4Tw",
        category = "Uncategorized"
    )
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