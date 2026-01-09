package com.example.practical9a

import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    // This tells the app to get data from the "/posts" web address
    @GET("posts")
    fun getPosts(): Call<List<Post>>
}