package br.stone.mobiletraining.samilasantos.chucknorrisfacts.common

import android.app.Application
import android.content.Intent
import br.stone.mobiletraining.samilasantos.domain.shareFact.ShareHandler

class AndroidShareHandler(private val application: Application) : ShareHandler {
    override fun share(fact: String) {
        val sentIntent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, fact)
            type = "text/plain"
            flags = Intent.FLAG_ACTIVITY_NEW_TASK
        }

        application.startActivity(sentIntent)
    }
}