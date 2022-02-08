package uz.adkhamjon.rickandmorty.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import uz.adkhamjon.rickandmorty.db.entity.CharacterEntity
import uz.adkhamjon.rickandmorty.models.Result

@Dao
interface MortyDao {
    @Insert
    suspend fun insert(characterEntity: CharacterEntity)
}