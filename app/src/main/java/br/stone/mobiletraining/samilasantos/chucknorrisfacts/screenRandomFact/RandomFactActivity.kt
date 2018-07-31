package br.stone.mobiletraining.samilasantos.chucknorrisfacts.screenRandomFact

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import br.stone.mobiletraining.samilasantos.chucknorrisfacts.R
import com.jakewharton.rxbinding2.view.clicks
import kotlinx.android.synthetic.main.activity_random_fact.*

class RandomFactActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_random_fact)

        defineIntents()
        setupView()
    }

    private fun defineIntents() {
        icon_search
            .clicks()
            .subscribe { }

        icon_share
            .clicks()
            .subscribe { }

        icon_update
            .clicks()
            .subscribe { }
    }

    private fun setupView() {}
}
