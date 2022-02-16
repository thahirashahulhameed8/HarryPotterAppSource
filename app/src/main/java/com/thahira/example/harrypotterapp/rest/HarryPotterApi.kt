package com.thahira.example.harrypotterapp.rest


import com.thahira.example.harrypotterapp.model.CharactersItem
import retrofit2.Response
import retrofit2.http.GET

interface HarryPotterApi {

    @GET(STAFF)
    suspend fun getStaffCharacters():Response<List<CharactersItem>>

    @GET(STUDENT)
    suspend fun getStudentCharacters():Response<List<CharactersItem>>

    companion object {
        const val BASE_URL = "http://hp-api.herokuapp.com/"
        private const val STAFF = "api/characters/staff"
        private const val STUDENT = "api/characters/students"
    }

}