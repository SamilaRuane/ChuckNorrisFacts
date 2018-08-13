package br.stone.mobiletraining.samilasantos.chucknorrisfacts.common

import android.support.v4.content.ContextCompat
import android.util.TypedValue
import br.stone.mobiletraining.samilasantos.chucknorrisfacts.R
import br.stone.mobiletraining.samilasantos.chucknorrisfacts.screenSearchFact.SearchFactContract
import br.stone.mobiletraining.samilasantos.domain.searchFact.uc.CategoryBackgroundColor
import br.stone.mobiletraining.samilasantos.domain.searchFact.uc.FactDescriptionFontSize
import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.item_button.*
import kotlinx.android.synthetic.main.item_fact.*
import kotlinx.android.synthetic.main.view_error.*

class FactsGroup(
    private val fact: SearchFactContract.Item,
    private val onShareClick: (factDescription: String) -> Unit
) : Item() {

    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.apply {
            val context = itemView.context
            textFactDescription.text = fact.description
            textCategory.text = fact.category
            textCategory.setBackgroundColor(when (fact.categoryBgColor) {
                CategoryBackgroundColor.GRAY -> R.color.category_gray_background
                CategoryBackgroundColor.BLUE -> R.color.category_blue_background
            }.run {
                ContextCompat.getColor(itemView.context, this)
            })

            textFactDescription.setTextSize(
                TypedValue.COMPLEX_UNIT_PX, when (fact.fontSize) {
                    FactDescriptionFontSize.REGULAR -> R.dimen.regular_font_size
                    FactDescriptionFontSize.LARGER -> R.dimen.larger_font_size
                }.run {
                    context.resources.getDimension(this)
                })

            imageIconShare.setOnClickListener { onShareClick(textFactDescription.text.toString()) }
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