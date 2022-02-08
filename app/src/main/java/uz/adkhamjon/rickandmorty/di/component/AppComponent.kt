package uz.adkhamjon.rickandmorty.di.component

import android.app.Application
import dagger.Component
import uz.adkhamjon.rickandmorty.db.database.AppDatabase
import uz.adkhamjon.rickandmorty.di.module.AppModule
import uz.adkhamjon.rickandmorty.di.module.DbModule
import uz.adkhamjon.rickandmorty.ui.ListFragment
import javax.inject.Singleton

@Singleton
@Component( modules = [AppModule::class,DbModule::class])
interface AppComponent {
    fun inject(listFragment: ListFragment)

//    @Component.Builder
////    interface Builder {
////        fun build(): AppComponent?
////        @BindsInstance
////        fun application(application: Application?): Builder
////    }
}