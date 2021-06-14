package com.faysal.assignementapp.data

import com.faysal.assignementapp.data.models.RandomUser
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


/*---- Api Endpoint -----*/
interface RandomUserApi {

    @GET("/api")
    suspend fun getRandomUserListResponse(
        @Query("page") page : Int = 1,
        @Query("results") results : Int = 10,
    ) : Response<RandomUser>


}