package com.edukaique.hp_api_android.data.api

import com.edukaique.hp_api_android.data.model.Character
import com.edukaique.hp_api_android.data.model.Spell
import retrofit2.http.GET
import retrofit2.http.Path

interface HpApiService {

    @GET("api/character/{id}")
    suspend fun getCharacterById(@Path("id") id: String): List<Character>

    @GET("api/characters/staff")
    suspend fun getStaff(): List<Character>

    @GET("api/characters/house/{house}")
    suspend fun getCharactersByHouse(@Path("house") house: String): List<Character>

    @GET("api/spells")
    suspend fun getSpells(): List<Spell>
}