package uz.adkhamjon.rickandmorty.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import uz.adkhamjon.rickandmorty.db.database.AppDatabase
import uz.adkhamjon.rickandmorty.network.ApiService
import uz.adkhamjon.rickandmorty.pagination.CharacterPaging
import uz.adkhamjon.rickandmorty.utils.Config
import javax.inject.Inject

class CharacterViewModel @Inject constructor(
    val apiService: ApiService,
    val appDatabase: AppDatabase
): ViewModel() {
    val characters = Pager(PagingConfig(Config.PAGE_SIZE)) {
        CharacterPaging(apiService,appDatabase)
    }.flow.cachedIn(viewModelScope)
}