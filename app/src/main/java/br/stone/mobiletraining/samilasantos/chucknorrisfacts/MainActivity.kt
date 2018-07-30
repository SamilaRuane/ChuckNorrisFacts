package br.stone.mobiletraining.samilasantos.chucknorrisfacts

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import br.stone.mobiletraining.samilasantos.domain.GetRandomicFact

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        GetRandomicFact()
    }
}
