package br.stone.mobiletraining.samilasantos.data.service.common

object FactScenarios {
    fun runWithError(errorCode: Int, test: RetrofitFactRepository.() -> Unit) {
        MockWebServerUtils.runWithMockServerErrorResponse(
            errorCode
        ) { baseUrl ->
            val retrofit =
                RetrofitManager.buildRetrofit(
                    baseUrl
                )
            test.invoke(
                RetrofitFactRepository(
                    retrofit
                )
            )
        }
    }

    fun runWithSuccess(body: String, test: RetrofitFactRepository.() -> Unit) {
        MockWebServerUtils.runWithMockServerOkResponse(
            body
        ) { baseUrl ->
            val retrofit =
                RetrofitManager.buildRetrofit(
                    baseUrl = baseUrl
                )
            test.invoke(
                RetrofitFactRepository(
                    retrofit
                )
            )
        }
    }

    fun runWithLongTimeDurationRequest(test: RetrofitFactRepository.() -> Unit) {
        val retrofit =
            RetrofitManager.buildRetrofit("http://127.0.1.1:1413/")
        val service =
            RetrofitFactRepository(retrofit)
        test(service)
    }
}