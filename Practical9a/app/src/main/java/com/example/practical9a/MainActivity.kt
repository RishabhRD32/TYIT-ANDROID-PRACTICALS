package com.example.practical9a

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: PostAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var tvStatus: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 1. Initialize Views
        recyclerView = findViewById(R.id.recyclerView)
        tvStatus = findViewById(R.id.tvStatus)
        val btnFetch = findViewById<Button>(R.id.btnFetch)

        // 2. Setup RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = PostAdapter(emptyList()) // Start with empty list
        recyclerView.adapter = adapter

        // 3. Setup Button Click
        btnFetch.setOnClickListener {
            fetchPosts()
        }
    }

    private fun fetchPosts() {
        tvStatus.text = "Loading..."

        // 4. Setup Retrofit (The library that does the internet work)
        val retrofit = Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val apiService = retrofit.create(ApiService::class.java)

        // 5. Make the call to get data
        val call = apiService.getPosts()

        call.enqueue(object : Callback<List<Post>> {
            override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
                if (response.isSuccessful) {
                    val posts = response.body()
                    if (posts != null) {
                        // Update the list with new data
                        adapter.updateData(posts)
                        tvStatus.text = "Loaded ${posts.size} posts successfully."
                    }
                } else {
                    tvStatus.text = "Error Code: ${response.code()}"
                }
            }

            override fun onFailure(call: Call<List<Post>>, t: Throwable) {
                tvStatus.text = "Failure: ${t.message}"
                Toast.makeText(this@MainActivity, "Check Internet Connection!", Toast.LENGTH_SHORT).show()
            }
        })
    }
}