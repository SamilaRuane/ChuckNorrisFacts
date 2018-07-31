package br.stone.mobiletraining.samilasantos.chucknorrisfacts.screenRandomFact

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import br.stone.mobiletraining.samilasantos.chucknorrisfacts.R
import br.stone.mobiletraining.samilasantos.chucknorrisfacts.di.diInject
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.activity_random_fact.*

class RandomFactActivity : AppCompatActivity() {

    private val viewModel by diInject<RandomFactViewModel>()
    private lateinit var disposable: Disposable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_random_fact)
    }

    override fun onStart() {
        super.onStart()

        defineIntents()
        setupView()
    }

    private fun defineIntents() {
        icon_update.setOnClickListener { viewModel.handleUpdateClick() }
    }

    private fun setupView() {

        disposable = viewModel.observeState()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { it.updateFact() }
    }

    private fun RandomFactContract.ViewState.updateFact() {
        when (this) {
            is RandomFactContract.ViewState.Error -> {
                progress_loading.visibility = View.GONE
                text_fact.visibility = View.VISIBLE
                text_fact.text = this.error.message
            }
            is RandomFactContract.ViewState.Success -> {
                progress_loading.visibility = View.GONE
                text_fact.visibility = View.VISIBLE
                text_fact.text = this.fact
            }
            is RandomFactContract.ViewState.Loading -> {
                text_fact.visibility = View.INVISIBLE
                progress_loading.visibility = View.VISIBLE
            }
        }
    }

    override fun onStop() {
        super.onStop()
        disposable.dispose()
    }
}
