package com.example.securevault.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.securevault.R
import com.example.securevault.data.entity.CardEntity

class CardsAdapter : RecyclerView.Adapter<CardsAdapter.CardViewHolder>() {

    private val cardList = mutableListOf<CardEntity>()

    // Function to update the list
    fun submitList(list: List<CardEntity>) {
        cardList.clear()
        cardList.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_card, parent, false) // replace with your item layout file
        return CardViewHolder(view)
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        holder.bind(cardList[position])
    }

    override fun getItemCount(): Int = cardList.size

    inner class CardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textTitle: TextView = itemView.findViewById(R.id.textTitle)
        private val textCardNumber: TextView = itemView.findViewById(R.id.textCardNumber)
        private val textCvv: TextView = itemView.findViewById(R.id.textCvv)
        private val textExpiryDate: TextView = itemView.findViewById(R.id.textExpiryDate)
        private val layoutDetails: View = itemView.findViewById(R.id.layoutDetails)

        fun bind(card: CardEntity) {
            textTitle.text = card.title
            textCardNumber.text = card.number
            textCvv.text = card.cvv
            textExpiryDate.text = card.expiryDate

            // Toggle details layout visibility on click
            itemView.setOnClickListener {
                layoutDetails.visibility =
                    if (layoutDetails.visibility == View.GONE) View.VISIBLE else View.GONE
            }
        }
    }
}
