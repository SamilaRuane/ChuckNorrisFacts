package br.stone.mobiletraining.samilasantos.chucknorrisfacts.screenSearchFact

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.inputmethod.InputMethodManager
import br.stone.mobiletraining.samilasantos.chucknorrisfacts.R
import br.stone.mobiletraining.samilasantos.chucknorrisfacts.common.ButtonGroup
import br.stone.mobiletraining.samilasantos.chucknorrisfacts.common.ErrorGroup
import br.stone.mobiletraining.samilasantos.chucknorrisfacts.common.FactsGroup
import br.stone.mobiletraining.samilasantos.chucknorrisfacts.common.InitialGroup
import br.stone.mobiletraining.samilasantos.chucknorrisfacts.common.LoadingGroup
import com.github.salomonbrys.kodein.bind
import com.github.salomonbrys.kodein.instance
import com.github.salomonbrys.kodein.provider
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import kodeinViewModelInjector.viewModelBinder
import kotlinx.android.synthetic.main.activity_search_fact.*

class SearchFactActivity : AppCompatActivity() {

    private val viewModel by viewModelBinder<SearchFactViewModel> {
        bind() from provider {
            SearchFactViewModel(
                getFactByQuery = instance(),
                calculateFontSize = instance(),
                processCategoryBgColor = instance()
            )
        }
    }
    private lateinit var disposable: Disposable

    private lateinit var groupAdapter: GroupAdapter<ViewHolder>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_fact)

        groupAdapter = GroupAdapter()
    }

    override fun onStart() {
        super.onStart()
        defineIntents()
        setupView()
    }

    private fun defineIntents() {
        imageIconSearch.setOnClickListener {
            closeKeyboard()
            viewModel.handleWithSearchClick(editSearchQuery.text.toString())
        }
    }

    private fun setupView() {
        recyclerFactsList.apply {
            layoutManager =
                LinearLayoutManager(
                    SearchFactActivity@ this.context,
                    LinearLayoutManager.VERTICAL,
                    false
                )
            adapter = groupAdapter

            disposable = viewModel.observeState()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { it.render(groupAdapter) }
        }
    }

    private fun SearchFactContract.ViewState.render(adapter: GroupAdapter<ViewHolder>) {
        when (this) {
            is SearchFactContract.ViewState.WaitingForInput -> adapter.add(InitialGroup())
            is SearchFactContract.ViewState.Success -> adapter.renderSuccessState(this.facts)
            is SearchFactContract.ViewState.Loading -> adapter.renderLoadingState()
            is SearchFactContract.ViewState.NoNetworkIssue -> adapter.renderStateWithRetryAndSettingsButton(getString(R.string.no_network_message))
            is SearchFactContract.ViewState.Timeout -> adapter.renderStateWithRetryAndSettingsButton(getString(R.string.timeout_message))
            is SearchFactContract.ViewState.UnavailableProvider -> adapter.renderErrorState(getString(R.string.unavailable_provider_message))
            is SearchFactContract.ViewState.UnexpectedData -> adapter.renderErrorState(getString(R.string.unexpected_data_message))
            is SearchFactContract.ViewState.NoMatchQuery -> adapter.renderErrorState(getString(R.string.query_not_match_message))
            is SearchFactContract.ViewState.MalformedQuery -> adapter.renderErrorState(getString(R.string.malformed_query_message))
        }
    }

    private fun GroupAdapter<ViewHolder>.renderStateWithRetryAndSettingsButton(error: String) {
        apply {
            clear()
            add(ErrorGroup(error))

            add(ButtonGroup(getString(R.string.retry_button)) {
                viewModel.handleWithSearchClick(
                    editSearchQuery.text.toString()
                )
            })

            add(ButtonGroup(getString(R.string.settings_button)) { launchSettings() })
        }
    }

    private fun GroupAdapter<ViewHolder>.renderErrorState(error: String) {
        apply {
            clear()
            add(ErrorGroup(error))
        }
    }

    private fun GroupAdapter<ViewHolder>.renderLoadingState() {
        apply {
            clear()
            add(LoadingGroup())
        }
    }

    private fun GroupAdapter<ViewHolder>.renderSuccessState(facts: List<SearchFactContract.Item>) {
        apply {
            clear()
            addAll(facts.renderItems())
        }
    }

    private fun List<SearchFactContract.Item>.renderItems(): List<FactsGroup> =
        map {
            FactsGroup(it)
        }

    private fun launchSettings() {
        startActivityForResult(
            Intent(Settings.ACTION_NETWORK_OPERATOR_SETTINGS), 0
        )
    }

    private fun closeKeyboard() {
        val inputMethodManager =
            getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(editSearchQuery.windowToken, 0)
    }

    override fun onStop() {
        disposable.dispose()
        super.onStop()
    }
}
