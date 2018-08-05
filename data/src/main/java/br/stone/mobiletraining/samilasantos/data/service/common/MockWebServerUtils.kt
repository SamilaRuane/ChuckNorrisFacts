package br.stone.mobiletraining.samilasantos.data.service.common

import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer

object MockWebServerUtils {

    fun runWithMockServerErrorResponse(
        errorCode: Int,
        testBody: MockWebServer.(baseUrl: String) -> Unit
    ) =
        runWithMockServer(
            {
                enqueue(
                    MockResponse()
                        .setResponseCode(errorCode)
                )
            },
            testBody
        )

    fun runWithMockServerOkResponse(
        body: String,
        returnCode: Int = 200,
        testBody: MockWebServer.(baseUrl: String) -> Unit
    ) =
        runWithMockServer(
            { enqueueOkResponse(body, returnCode) },
            testBody
        )

    private fun runWithMockServer(
        enqueueBody: MockWebServer.() -> Unit,
        testBody: MockWebServer.(baseUrl: String) -> Unit
    ) {
        MockWebServer().use { webServer ->
            enqueueBody.invoke(webServer)

            webServer.start()

            testBody.invoke(webServer, webServer.url("/").toString())
        }
    }

    private fun MockWebServer.enqueueOkResponse(body: String, returnCode: Int) =
        enqueue(
            MockResponse()
                .setResponseCode(returnCode)
                .setHeader("content-type", "application/json")
                .setBody(body)
        )
}