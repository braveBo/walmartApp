package com.block.walmart.hw

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CountryAdapter(private var countries: List<Country> = emptyList()): RecyclerView.Adapter<CountryAdapter.ViewHolder>() {
    private var myCountries: List<Country> = countries

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nameTextView: TextView = itemView.findViewById(R.id.country_name)
        private val regionTextView: TextView = itemView.findViewById(R.id.country_region)
        private val codeTextView: TextView = itemView.findViewById(R.id.country_code)
        private val capitalTextView: TextView = itemView.findViewById(R.id.country_capital)

        fun bind(country: Country) {
            nameTextView.text = country.name
            regionTextView.text = country.region
            codeTextView.text = country.code
            capitalTextView.text = country.capital
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_country, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(myCountries[position])
    }

    override fun getItemCount(): Int {
        return myCountries.size
    }

    fun updateList(countries: List<Country>) {
        myCountries = countries
        notifyDataSetChanged()
    }
}

data class Country(
    val name: String,
    val code: String,
    val region: String,
    val capital: String
)