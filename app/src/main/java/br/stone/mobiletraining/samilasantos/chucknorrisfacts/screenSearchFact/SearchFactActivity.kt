package br.stone.mobiletraining.samilasantos.chucknorrisfacts.screenSearchFact

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import br.stone.mobiletraining.samilasantos.chucknorrisfacts.R
import br.stone.mobiletraining.samilasantos.chucknorrisfacts.common.FactsGroup
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.activity_search_fact.*

class SearchFactActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_fact)
    }

    override fun onStart() {
        super.onStart()
        defineIntents()
        setupView()
    }

    private fun defineIntents() {
        imageIconSearch.setOnClickListener { }
    }

    private fun setupView() {
        val groupAdapter = GroupAdapter<ViewHolder>()
        recyclerFactsList.apply {
            layoutManager =
                LinearLayoutManager(
                    SearchFactActivity@ this.context,
                    LinearLayoutManager.VERTICAL,
                    false
                )
            adapter = groupAdapter
            addItemsToAdapter(adapter = groupAdapter)
        }
    }

    private fun addItemsToAdapter(adapter: GroupAdapter<ViewHolder>) {
        adapter.addAll(getMockedFacts().map { FactsGroup(it) })
    }

    override fun onStop() {
        super.onStop()
    }
}
