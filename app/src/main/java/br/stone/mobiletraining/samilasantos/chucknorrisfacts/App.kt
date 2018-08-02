package br.stone.mobiletraining.samilasantos.chucknorrisfacts

import android.app.Application
import br.stone.mobiletraining.samilasantos.chucknorrisfacts.di.Injector

class App : Application() {

    lateinit var injector: Injector

    override fun onCreate() {
        super.onCreate()
        injector = Injector(this)
    }
}