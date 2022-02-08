package uz.adkhamjon.rickandmorty.pagination
import androidx.paging.PagingSource
import androidx.paging.PagingState
import uz.adkhamjon.rickandmorty.db.database.AppDatabase
import uz.adkhamjon.rickandmorty.db.entity.CharacterEntity
import uz.adkhamjon.rickandmorty.models.Result
import uz.adkhamjon.rickandmorty.network.ApiService
import java.lang.Exception
import javax.inject.Inject

class CharacterPaging @Inject constructor(
    val apiService: ApiService,
    val appDatabase: AppDatabase
    ): PagingSource<Int, Result>() {
    override fun getRefreshKey(state: PagingState<Int, Result>): Int? {
        return state.anchorPosition
    }
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Result> {
        try {
            val nextPageNumber = params.key ?: 1
            val character = apiService.getCharacters(page = nextPageNumber)
            if (nextPageNumber > 1) {
                character.results.forEach{
                    val data = CharacterEntity(
                        name = it.name,
                        status = it.status,
                        location = it.location.name,
                        imageUrl = it.image)
                    appDatabase.mortyDao().insert(data)
                }
                return LoadResult.Page(character.results, nextPageNumber - 1, nextPageNumber + 1)
            } else {
                character.results.forEach{
                    val data = CharacterEntity(
                        name = it.name,
                        status = it.status,
                        location = it.location.name,
                        imageUrl = it.image)
                    appDatabase.mortyDao().insert(data)
                }
                return LoadResult.Page(character.results, null, nextPageNumber + 1)
            }
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }
}