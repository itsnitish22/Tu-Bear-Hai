package com.nitishsharma.tubeerhai.api

import com.nitishsharma.tubeerhai.api.models.Beer
import retrofit2.http.GET
import retrofit2.http.Query

interface PunkApiService {
    @GET("https://api.punkapi.com/v2/beers")
    suspend fun getBeersPaged(
        @Query("page") pageNum: Int,
        @Query("per_page") pageSize: Int
    ): ArrayList<Beer>
}