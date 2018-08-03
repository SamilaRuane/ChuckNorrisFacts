package br.stone.mobiletraining.samilasantos.chucknorrisfacts.screenRandomFact

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import br.stone.mobiletraining.samilasantos.chucknorrisfacts.R
import br.stone.mobiletraining.samilasantos.chucknorrisfacts.di.diInject
import br.stone.mobiletraining.samilasantos.chucknorrisfacts.extensions.dialog
import br.stone.mobiletraining.samilasantos.domain.common.IntegrationExceptions
import br.stone.mobiletraining.samilasantos.domain.common.NetworkIssues
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.activity_random_fact.icon_update
import kotlinx.android.synthetic.main.activity_random_fact.progress_loading
import kotlinx.android.synthetic.main.activity_random_fact.text_fact

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
                when (this.error) {
                    is NetworkIssues.NoNetwork -> {
                        errorState()
                        showDialogNoNetwork()
                    }
                    is NetworkIssues.Timeout -> {
                        errorState()
                        showDialogTimeoutException()
                    }
                    is IntegrationExceptions.UnavailableProvider -> {
                        errorState()
                        showDialogUnavailableProvider()
                    }
                    else -> errorState()
                }
            }
            is RandomFactContract.ViewState.Success -> successState(this.fact)
            is RandomFactContract.ViewState.Loading -> loadingState()
        }
    }

    private fun successState(fact: String) {
        progress_loading.visibility = View.GONE
        text_fact.visibility = View.VISIBLE
        text_fact.text = fact
    }

    private fun errorState() {
        progress_loading.visibility = View.GONE
        text_fact.visibility = View.VISIBLE
    }

    private fun loadingState() {
        text_fact.visibility = View.INVISIBLE
        progress_loading.visibility = View.VISIBLE
    }

    private fun showDialogNoNetwork() {
        dialog(msg = getString(R.string.no_network_message),
            positiveButton = getString(R.string.retry_button),
            negativeButton = getString(R.string.cancel_button),
            listener = { dialog, _ ->
                dialog.dismiss()
                viewModel.handleUpdateClick()
            }).show()
    }

    private fun showDialogTimeoutException() {
        dialog(msg = getString(R.string.timeout_message),
            positiveButton = getString(R.string.retry_button),
            negativeButton = getString(R.string.cancel_button),
            listener = { dialog, _ -> dialog.dismiss() }).show()
    }

    private fun showDialogUnavailableProvider() {
        dialog(msg = getString(R.string.unavailable_provider_message),
            positiveButton = getString(R.string.ok_button),
            negativeButton = "",
            listener = { dialog, _ -> dialog.dismiss() }).show()
    }

    override fun onStop() {
        super.onStop()
        disposable.dispose()
    }
}
