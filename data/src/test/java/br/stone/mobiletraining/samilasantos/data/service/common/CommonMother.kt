package br.stone.mobiletraining.samilasantos.data.service.common

import br.stone.mobiletraining.samilasantos.data.service.MockWebServerUtils

object CommonMother {

    val succesBodyId = "0PlGg24YSX--haND5nj4Tw"
    val successbody = """
        {
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