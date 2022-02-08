package uz.adkhamjon.rickandmorty.network

import retrofit2.http.GET
import retrofit2.http.Query
import uz.adkhamjon.rickandmorty.models.CharacterModel

interface ApiService {
    @GET("character")
    suspend fun getCharacters(
        @Query("page") page: Int
    ): CharacterModel
}