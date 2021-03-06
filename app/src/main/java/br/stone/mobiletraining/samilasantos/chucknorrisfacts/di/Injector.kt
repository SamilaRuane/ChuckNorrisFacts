package br.stone.mobiletraining.samilasantos.chucknorrisfacts.di

import android.app.Activity
import android.app.Application
import br.stone.mobiletraining.samilasantos.chucknorrisfacts.App
import br.stone.mobiletraining.samilasantos.chucknorrisfacts.common.AndroidShareHandler
import br.stone.mobiletraining.samilasantos.chucknorrisfacts.screenRandomFact.RandomFactViewModel
import br.stone.mobiletraining.samilasantos.data.service.common.RetrofitFactRepository
import br.stone.mobiletraining.samilasantos.data.service.common.RetrofitManager
import br.stone.mobiletraining.samilasantos.domain.randomFact.RandomFactRepository
import br.stone.mobiletraining.samilasantos.domain.randomFact.uc.GetRandomFact
import br.stone.mobiletraining.samilasantos.domain.searchFact.SearchFactRepository
import br.stone.mobiletraining.samilasantos.domain.searchFact.uc.CalculateFactDescriptionFontSize
import br.stone.mobiletraining.samilasantos.domain.searchFact.uc.GetFactByQuery
import br.stone.mobiletraining.samilasantos.domain.searchFact.uc.ProcessCategoryBgColor
import br.stone.mobiletraining.samilasantos.domain.shareFact.FactSharer
import br.stone.mobiletraining.samilasantos.domain.shareFact.ShareHandler
import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.bind
import com.github.salomonbrys.kodein.conf.ConfigurableKodein
import com.github.salomonbrys.kodein.instance
import com.github.salomonbrys.kodein.provider
import com.github.salomonbrys.kodein.singleton
import kodeinViewModelInjector.KodeinViewModelInjector
import retrofit2.Retrofit

class Injector(private val application: Application) {

    init {
        KodeinViewModelInjector.setContainerProvider { configurableGraph }
    }

    fun reconfigureGraph(body: Kodein.Builder.() -> Unit) {
        dependencies = dependenciesBuilder()

        with(configurableGraph) {
            clear()
            addExtend(dependencies)
            addConfig(body)
        }
    }

    val configurableGraph by lazy {
        ConfigurableKodein(mutable = true)
            .apply {
                addExtend(dependencies)
            }
    }

    private val dependenciesBuilder = {
        Kodein {
            bind<RandomFactViewModel>() with provider {
                RandomFactViewModel(instance(), instance())
            }

            bind<RandomFactRepository>() with singleton {
                RetrofitFactRepository(instance())
            }

            bind<String>() with singleton {
                "https://api.chucknorris.io/"
            }

            bind<Retrofit>() with singleton {
                RetrofitManager.buildRetrofit(instance())
            }

            bind<SearchFactRepository>() with singleton {
                RetrofitFactRepository(instance())
            }

            bind<GetRandomFact>() with provider {
                GetRandomFact(instance())
            }

            bind<GetFactByQuery>() with provider {
                GetFactByQuery(instance())
            }

            bind<CalculateFactDescriptionFontSize>() with provider {
                CalculateFactDescriptionFontSize()
            }

            bind<ProcessCategoryBgColor>() with provider {
                ProcessCategoryBgColor()
            }

            bind<FactSharer>() with provider {
                FactSharer(instance())
            }

            bind<ShareHandler>() with provider {
                AndroidShareHandler(instance())
            }

            bind<Application>() with singleton {
                application
            }
        }
    }

    private var dependencies = dependenciesBuilder()
}

inline fun <reified T : Any> Activity.diInject(tag: Any? = null) =
    lazy {
        (application as App).injector.configurableGraph.instance<T>(tag)
    }