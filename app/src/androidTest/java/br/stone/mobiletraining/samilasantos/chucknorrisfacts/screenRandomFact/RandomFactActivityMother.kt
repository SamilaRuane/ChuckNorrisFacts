package br.stone.mobiletraining.samilasantos.chucknorrisfacts.screenRandomFact

import android.support.test.InstrumentationRegistry
import br.stone.mobiletraining.samilasantos.chucknorrisfacts.R

object RandomFactActivityMother {
    val noNetworkFeedback: () -> String = { getContext().getString(R.string.no_network_message) }
    val timeoutFeedback: () -> String = { getContext().getString(R.string.timeout_message) }
    val unavailableProviderFeedback: () -> String =
        { getContext().getString(R.string.unavailable_provider_message) }
    val unexpectedDataFeedback: () -> String =
        { getContext().getString(R.string.unexpected_data_message) }

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

    private fun getContext() = InstrumentationRegistry.getTargetContext()
}