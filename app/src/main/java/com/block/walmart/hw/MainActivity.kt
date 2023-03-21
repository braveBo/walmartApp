package com.block.walmart.hw

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.block.walmart.hw.networks.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: CountryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        adapter = CountryAdapter()
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        fetchCountries()
    }

    private fun fetchCountries() {
       GlobalScope.launch {
           try {
               val countries = RetrofitClient.countryApiService.getCountries()
               withContext(Dispatchers.Main) {
                   adapter.updateList(countries)
               }
           } catch (e: Exception) {
               withContext(Dispatchers.Main) {
                   Toast.makeText(this@MainActivity, "Error: ${e.message}", Toast.LENGTH_SHORT)
               }
           }
       }
    }
}
