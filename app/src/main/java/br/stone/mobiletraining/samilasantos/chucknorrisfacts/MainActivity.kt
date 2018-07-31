package br.stone.mobiletraining.samilasantos.chucknorrisfacts

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import br.stone.mobiletraining.samilasantos.data.RetrofitManager
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        RetrofitManager
            .chuckNorrisFactsService()
            .getRandomFact()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                txt_hello.text = it.value
            }
    }
}
