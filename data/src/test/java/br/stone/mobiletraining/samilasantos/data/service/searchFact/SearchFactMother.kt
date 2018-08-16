package br.stone.mobiletraining.samilasantos.data.service.searchFact

import br.stone.mobiletraining.samilasantos.domain.common.Fact

object SearchFactMother {
    val successBodyObject = arrayOf(
        Fact(
            id = "v5bjnMaRTk2dW3lCGAfkVQ",
            description = "Ok im back. Chuck Norris was secretly included for Mortal Kombat X. The developers then changed their minds and removed him because 1 hit from Chuck Norris= a fatality.",
            url = "https://api.chucknorris.io/jokes/v5bjnMaRTk2dW3lCGAfkVQ",
            category = "Uncategorized"
        ),
        Fact(
            id = "VZg8Mxg8TGeEc0qq3u4GOA",
            description = "Once a player found Chuck Norris on super smash bros for 3DS. He was instantly beaten from Chuck Norris when the Challenger round started. Developers of super smash bros said that the Chuck Norris guy was a easter egg. It was removed for being too powerful after 2 days.",
            url = "https://api.chucknorris.io/jokes/VZg8Mxg8TGeEc0qq3u4GOA",
            category = "Uncategorized"
        )
    )
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
      "result":[]
    }
    """.trimIndent()
}