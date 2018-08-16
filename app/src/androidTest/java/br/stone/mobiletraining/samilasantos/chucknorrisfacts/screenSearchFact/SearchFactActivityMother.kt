package br.stone.mobiletraining.samilasantos.chucknorrisfacts.screenSearchFact

import android.content.Context
import android.support.test.InstrumentationRegistry
import br.stone.mobiletraining.samilasantos.chucknorrisfacts.R

object SearchFactActivityMother {

    val noNetworkFeedback: () -> String = { getContext().getString(R.string.no_network_message) }
    val timeoutFeedback: () -> String = { getContext().getString(R.string.timeout_message) }
    val infoNotFoundFeedback: () -> String = { getContext().getString(R.string.query_not_match_message) }
    val malformedQueryFeedback: () -> String = { getContext().getString(R.string.malformed_query_message) }
    val unavailableProviderFeedback: () -> String = { getContext().getString(R.string.unavailable_provider_message) }
    val unexpectedDataFeedback: () -> String = { getContext().getString(R.string.unexpected_data_message) }
    val queryNotMatchFeedback: () -> String = { getContext().getString(R.string.query_not_match_message) }
    val successBody = """
        {
   "total":2,
   "result":[
      {
         "category":null,
         "icon_url":"https:\/\/assets.chucknorris.host\/img\/avatar\/chuck-norris.png",
         "id":"v5bjnMaRTk2dW3lCGAfkVQ",
         "url":"https:\/\/api.chucknorris.io\/jokes\/v5bjnMaRTk2dW3lCGAfkVQ",
         "value":"Ok im back. Chuck Norris was secretly included for Mortal Kombat X. The developers then changed their minds and removed him because 1 hit from Chuck Norris= a fatality."
      },
      {
         "category":null,
         "icon_url":"https:\/\/assets.chucknorris.host\/img\/avatar\/chuck-norris.png",
         "id":"VZg8Mxg8TGeEc0qq3u4GOA",
         "url":"https:\/\/api.chucknorris.io\/jokes\/VZg8Mxg8TGeEc0qq3u4GOA",
         "value":"Once a player found Chuck Norris on super smash bros for 3DS. He was instantly beaten from Chuck Norris when the Challenger round started. Developers of super smash bros said that the Chuck Norris guy was a easter egg. It was removed for being too powerful after 2 days."
      }
   ]
}
    """.trimIndent()

    val malFormedBody = """
        {
   "total":2,
   "result":[
   "intruder"yyuy
      {
         "category":null,
         "icon_url":"https:\/\/assets.chucknorris.host\/img\/avatar\/chuck-norris.png",
         "id":"v5bjnMaRTk2dW3lCGAfkVQ",
         "url":"https:\/\/api.chucknorris.io\/jokes\/v5bjnMaRTk2dW3lCGAfkVQ",
         "value":"Ok im back. Chuck Norris was secretly included for Mortal Kombat X. The developers then changed their minds and removed him because 1 hit from Chuck Norris= a fatality."
      },
      {
         "category":null,
         "icon_url":"https:\/\/assets.chucknorris.host\/img\/avatar\/chuck-norris.png",
         "id":"VZg8Mxg8TGeEc0qq3u4GOA",
         "url":"https:\/\/api.chucknorris.io\/jokes\/VZg8Mxg8TGeEc0qq3u4GOA",
         "value":"Once a player found Chuck Norris on super smash bros for 3DS. He was instantly beaten from Chuck Norris when the Challenger round started. Developers of super smash bros said that the Chuck Norris guy was a easter egg. It was removed for being too powerful after 2 days."
      }
   ]
}
    """.trimIndent()

    val emptyBody = """
        {
   "total":2,
   "result":[]}
    """.trimIndent()

    private fun getContext(): Context = InstrumentationRegistry.getTargetContext()
}
