package uz.adkhamjon.rickandmorty.di.module

import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import uz.adkhamjon.rickandmorty.network.ApiService
import uz.adkhamjon.rickandmorty.utils.Config
import javax.inject.Singleton

@Module
class AppModule{
    @Singleton
    @Provides
    fun provideBaseUrl():String= Config.BASE_URL

    @Singleton
    @Provides
    fun provideGsonConvertorFactory(): GsonConverterFactory = GsonConverterFactory.create()
    
    @Singleton
    @Provides
    fun provideRetrofit(
        baseUrl:String,
        gsonConverterFactory: GsonConverterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(gsonConverterFactory)
            .build()
    }

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): ApiService =retrofit.create(ApiService::class.java)
}