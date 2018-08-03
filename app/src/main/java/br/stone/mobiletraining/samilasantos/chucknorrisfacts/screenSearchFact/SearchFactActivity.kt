package br.stone.mobiletraining.samilasantos.chucknorrisfacts.screenSearchFact

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import br.stone.mobiletraining.samilasantos.chucknorrisfacts.R
import kotlinx.android.synthetic.main.activity_search_fact.*

class SearchFactActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_fact)

        recyclerFactsList.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerFactsList.adapter = FactAdapter(getMockedFacts())
    }
}
