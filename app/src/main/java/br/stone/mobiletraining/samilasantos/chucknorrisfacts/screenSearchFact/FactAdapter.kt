package br.stone.mobiletraining.samilasantos.chucknorrisfacts.screenSearchFact

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.stone.mobiletraining.samilasantos.chucknorrisfacts.R
import br.stone.mobiletraining.samilasantos.domain.common.Fact
import kotlinx.android.synthetic.main.item_fact.view.*

class FactAdapter(val facts: List<Fact>) : RecyclerView.Adapter<FactAdapter.FactViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FactViewHolder =
        FactViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_fact,
                parent,
                false
            )
        )

    override fun getItemCount(): Int = facts.size

    override fun onBindViewHolder(holder: FactViewHolder, position: Int) {
        holder.bind(fact = facts[position])
    }

    class FactViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(fact: Fact) {
            itemView.textFactDescription.text = fact.description
            itemView.textCategory.text = fact.category
        }
    }
}