package com.faysal.assignementapp.main

import com.faysal.assignementapp.data.models.RandomUser
import com.faysal.assignementapp.utility.Resource

interface MainRepository {
    suspend fun getRandomUserListResponse() : Resource<RandomUser>

}