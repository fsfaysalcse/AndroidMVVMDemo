package com.faysal.assignementapp.main

import com.faysal.assignementapp.data.RandomUserApi
import com.faysal.assignementapp.data.models.RandomUser
import com.faysal.assignementapp.utility.Resource
import javax.inject.Inject

class DefaultMainRepository @Inject constructor(
   private val api : RandomUserApi
) : MainRepository{
    override suspend fun getRandomUserListResponse(): Resource<RandomUser> {
        return try {
            val response = api.getRandomUserListResponse()
            val result = response.body()
            if (result !=null && response.isSuccessful){
                Resource.Success(result)
            }else{
                Resource.Error(response.message())
            }
        } catch (e : Exception){
            Resource.Error(e.message ?: "Something Wrong ! Please try again")
        }
    }
}