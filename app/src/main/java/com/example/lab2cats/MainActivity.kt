package com.example.lab2cats

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lab2cats.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import com.example.lab2cats.network.ApiFactory.apiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var adapter: CatListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        adapter = CatListAdapter(this)
        binding.rvServiceList.adapter = adapter
        binding.rvServiceList.layoutManager = LinearLayoutManager(this)

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String): Boolean {
                if (newText.isEmpty()) {
                    adapter.submitList(emptyList())
                } else {
                    CoroutineScope(Dispatchers.IO).launch {
                        val movieList = apiService.catList(newText)
                        runOnUiThread {
                            if (movieList.isEmpty()) {
                                adapter.submitList(emptyList())
                                binding.textView6.visibility = View.VISIBLE
                            } else {
                                binding.textView6.visibility = View.GONE
                                adapter.submitList(movieList)
                            }
                        }
                    }
                }
                return true
            }
            override fun onQueryTextSubmit(query: String): Boolean {
                return true
            }
        })
    }
}