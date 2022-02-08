package uz.adkhamjon.rickandmorty.db.database

import androidx.room.Database
import androidx.room.RoomDatabase
import uz.adkhamjon.rickandmorty.db.dao.MortyDao
import uz.adkhamjon.rickandmorty.db.entity.CharacterEntity


@Database(entities = [CharacterEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun mortyDao(): MortyDao

}