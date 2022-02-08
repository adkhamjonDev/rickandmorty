package uz.adkhamjon.rickandmorty.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "characters")
data class CharacterEntity (
    @PrimaryKey(autoGenerate = true)
    val id: Int=0,
    val name: String,
    val status: String,
    val location: String,
    val imageUrl: String
    )