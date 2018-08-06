package br.stone.mobiletraining.samilasantos.chucknorrisfacts.common

import br.stone.mobiletraining.samilasantos.chucknorrisfacts.R
import br.stone.mobiletraining.samilasantos.domain.common.Fact
import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.item_button.*
import kotlinx.android.synthetic.main.item_fact.*
import kotlinx.android.synthetic.main.view_error.*

class FactsGroup(private val fact: Fact) : Item() {
    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.apply {
            textFactDescription.text = fact.description
            textCategory.text = fact.category
        }
    }

    override fun getLayout(): Int = R.layout.item_fact

    override fun isSameAs(other: com.xwray.groupie.Item<*>?): Boolean =
        (other as? FactsGroup)?.fact == fact
}

class LoadingGroup : Item(0) {
    override fun bind(viewHolder: ViewHolder, position: Int) = Unit

    override fun getLayout(): Int = R.layout.item_loading
}

class ErrorGroup(private val errorMessage: String) :
    Item() {
    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.apply {
            textErrorInfo.text = errorMessage
        }
    }

    override fun getLayout(): Int = R.layout.view_error

    override fun isSameAs(other: com.xwray.groupie.Item<*>?): Boolean =
        (other as? ErrorGroup)?.errorMessage == errorMessage
}

class ButtonGroup(private val name: String, private val onClick: () -> Unit) : Item() {
    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.apply {
            buttonError.text = name
            buttonError.setOnClickListener {
                onClick()
            }
        }
    }

    override fun getLayout(): Int = R.layout.item_button

    override fun isSameAs(other: com.xwray.groupie.Item<*>?): Boolean =
        (other as? ButtonGroup)?.name == name
}

class InitialGroup : Item(1) {
    override fun bind(viewHolder: ViewHolder, position: Int) = Unit

    override fun getLayout(): Int = R.layout.item_initial_message
}