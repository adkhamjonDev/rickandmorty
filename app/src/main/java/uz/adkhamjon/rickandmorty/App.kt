package uz.adkhamjon.rickandmorty
import android.app.Application
import uz.adkhamjon.rickandmorty.di.component.AppComponent
import uz.adkhamjon.rickandmorty.di.component.DaggerAppComponent
import uz.adkhamjon.rickandmorty.di.module.AppModule
import uz.adkhamjon.rickandmorty.di.module.DbModule


class App:Application() {
    companion object{ lateinit var appComponent: AppComponent }
    override fun onCreate() {
        super.onCreate()
        appComponent=DaggerAppComponent.builder().
        appModule(AppModule()).
        dbModule(DbModule(this)).build()
    }
}