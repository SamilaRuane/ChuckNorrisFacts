package br.stone.mobiletraining.samilasantos.chucknorrisfacts.di

import android.app.Activity
import android.app.Application
import br.stone.mobiletraining.samilasantos.chucknorrisfacts.App
import br.stone.mobiletraining.samilasantos.chucknorrisfacts.screenRandomFact.RandomFactViewModel
import br.stone.mobiletraining.samilasantos.data.service.common.ChuckNorrisApi
import br.stone.mobiletraining.samilasantos.data.service.common.ChuckNorrisService
import br.stone.mobiletraining.samilasantos.data.service.common.RetrofitManager
import br.stone.mobiletraining.samilasantos.domain.randomFact.RandomFactRepository
import br.stone.mobiletraining.samilasantos.domain.randomFact.uc.GetRandomFact
import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.bind
import com.github.salomonbrys.kodein.conf.ConfigurableKodein
import com.github.salomonbrys.kodein.instance
import com.github.salomonbrys.kodein.provider
import com.github.salomonbrys.kodein.singleton

class Injector(private val application: Application) {

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
                RandomFactViewModel(instance())
            }

            bind<RandomFactRepository>() with singleton {
                ChuckNorrisService(instance())
            }

            bind<String>() with singleton {
                "https://api.chucknorris.io/"
            }

            bind<ChuckNorrisApi>() with singleton {
                RetrofitManager.chuckNorrisFactsApi(instance())
            }

            bind<GetRandomFact>() with provider {
                GetRandomFact(instance())
            }
        }
    }

    private var dependencies = dependenciesBuilder()
}

inline fun <reified T : Any> Activity.diInject(tag: Any? = null) =
    lazy {
        (application as App).injector.configurableGraph.instance<T>(tag)
    }