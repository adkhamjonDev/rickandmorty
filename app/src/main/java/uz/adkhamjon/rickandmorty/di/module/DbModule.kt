package uz.adkhamjon.rickandmorty.di.module

import android.app.Application
import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import uz.adkhamjon.rickandmorty.db.dao.MortyDao
import uz.adkhamjon.rickandmorty.db.database.AppDatabase
import javax.inject.Singleton

@Module
class DbModule(val context: Context) {

    @Provides
    @Singleton
    fun provideContext(): Context = context

    @Provides
    @Singleton
    fun provideAppDatabase(): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, "my_db")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideCharacterDao(appDatabase: AppDatabase): MortyDao = appDatabase.mortyDao()
}