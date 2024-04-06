package com.example.lab2cats.network

import com.example.lab2cats.Cat
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ApiService {

    @GET("cats")
    @Headers("X-Api-Key: EgTUe72csmYo1Oyi79ZOoQ==2q0WJwD05DHzn0Sy")
    suspend fun catList(
        @Query("name") name: String? = null,
        @Query("min_weight") minWeight: Int? = null,
        @Query("max_weight") maxWeight: Int? = null,
        @Query("min_life_expectancy") minLifeExpectancy: Int? = null,
        @Query("max_life_expectancy") maxLifeExpectancy: Int? = null,
        @Query("shedding") shedding: Int? = null,
        @Query("family_friendly") familyFriendly: Int? = null,
        @Query("playfulness") playfulness: Int? = null,
        @Query("grooming") grooming: Int? = null,
        @Query("other_pets_friendly") otherPetsFriendly: Int? = null,
        @Query("children_friendly") childrenFriendly: Int? = null,
        @Query("offset") offset: Int? = null
    ): List<Cat>

}