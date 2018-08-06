package br.stone.mobiletraining.samilasantos.data.service.common

import br.stone.mobiletraining.samilasantos.domain.common.Fact

object FactMother {

    val successBodyObject = Fact(
        id = "0PlGg24YSX--haND5nj4Tw",
        description = "Chuck Norris ride into town on Friday, stayed three Nights, the rode out again on Friday. On a horse named Steve.",
        url = "https://api.chucknorris.io/jokes/0PlGg24YSX--haND5nj4Tw",
        category = "Uncategorized"
    )
    val successbody = """
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

    fun runWithError(errorCode: Int, test: RetrofitFactRepository.() -> Unit) {
        MockWebServerUtils.runWithMockServerErrorResponse(errorCode) { baseUrl ->
            val retrofit = RetrofitManager.buildRetrofit(baseUrl)
            test.invoke(RetrofitFactRepository(retrofit))
        }
    }

    fun runWithSuccess(body: String, test: RetrofitFactRepository.() -> Unit) {
        MockWebServerUtils.runWithMockServerOkResponse(body) { baseUrl ->
            val retrofit = RetrofitManager.buildRetrofit(baseUrl = baseUrl)
            test.invoke(RetrofitFactRepository(retrofit))
        }
    }
}