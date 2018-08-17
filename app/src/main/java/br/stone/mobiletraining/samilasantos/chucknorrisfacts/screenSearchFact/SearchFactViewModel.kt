package br.stone.mobiletraining.samilasantos.chucknorrisfacts.screenSearchFact

import android.arch.lifecycle.ViewModel
import br.stone.mobiletraining.samilasantos.domain.common.IntegrationExceptions
import br.stone.mobiletraining.samilasantos.domain.common.NetworkIssues
import br.stone.mobiletraining.samilasantos.domain.searchFact.GetFactByQueryResult
import br.stone.mobiletraining.samilasantos.domain.searchFact.SearchFactExceptions
import br.stone.mobiletraining.samilasantos.domain.searchFact.uc.CalculateFactDescriptionFontSize
import br.stone.mobiletraining.samilasantos.domain.searchFact.uc.GetFactByQuery
import br.stone.mobiletraining.samilasantos.domain.searchFact.uc.ProcessCategoryBgColor
import br.stone.mobiletraining.samilasantos.domain.shareFact.FactSharer
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import java.util.concurrent.TimeUnit

class SearchFactViewModel(
    private val getFactByQuery: GetFactByQuery,
    private val calculateFontSize: CalculateFactDescriptionFontSize,
    private val processCategoryBgColor: ProcessCategoryBgColor,
    private val factSharer: FactSharer
) : ViewModel() {

    private val searchIntentObservable = PublishSubject.create<String>()
    private var actualState: SearchFactContract.ViewState =
        SearchFactContract.ViewState.WaitingForInput

    fun handleWithSearchClick(query: String) {
        searchIntentObservable.onNext(query)
    }

    fun observeState(): Observable<SearchFactContract.ViewState> = searchIntentObservable
        .throttleFirst(500, TimeUnit.MILLISECONDS)
        .switchMap {
            getFactByQuery.using(query = it)
                .map {
                    when (it) {
                        is GetFactByQueryResult.Success -> SearchFactContract.ViewState.Success(
                            it.facts.map {
                                SearchFactContract.Item(
                                    id = it.id,
                                    description = it.description,
                                    url = it.url,
                                    category = it.category,
                                    fontSize = calculateFontSize.using(it.description.length),
                                    categoryBgColor = processCategoryBgColor.using(it.category)
                                )
                            })
                        is GetFactByQueryResult.Error ->
                            when (it.error) {
                                is NetworkIssues.NoNetwork -> SearchFactContract.ViewState.NoNetworkIssue
                                is NetworkIssues.Timeout -> SearchFactContract.ViewState.Timeout
                                is IntegrationExceptions.UnavailableProvider -> SearchFactContract.ViewState.UnavailableProvider
                                is SearchFactExceptions.QueryNotMatchException -> SearchFactContract.ViewState.NoMatchQuery
                                is IntegrationExceptions.InfoNotFound -> SearchFactContract.ViewState.NoMatchQuery
                                is SearchFactExceptions.MalFormedQuery -> SearchFactContract.ViewState.MalformedQuery
                                is IntegrationExceptions.UnexpectedData -> SearchFactContract.ViewState.UnexpectedData
                                else -> SearchFactContract.ViewState.NoMatchQuery
                            }
                    }
                }
                .toObservable()
                .startWith(SearchFactContract.ViewState.Loading)
        }.startWith(actualState)
        .doOnNext { actualState = it }

    fun handleWithShareClick(fact: String) {
        factSharer.share(fact)
    }
}