package uz.adkhamjon.rickandmorty.di.component

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import uz.adkhamjon.rickandmorty.di.module.NetworkModule
import uz.adkhamjon.rickandmorty.ui.ListFragment
import javax.inject.Singleton


@Singleton
@Component(modules = [NetworkModule::class])
interface AppComponent {
    fun inject(listFragment: ListFragment)
    @Component.Builder
    interface Builder {
        fun build(): AppComponent?

        @BindsInstance
        fun application(application: Application?): Builder
    }
}