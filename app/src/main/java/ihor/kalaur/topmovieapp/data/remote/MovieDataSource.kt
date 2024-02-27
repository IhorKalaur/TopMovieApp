package ihor.kalaur.topmovieapp.data.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import ihor.kalaur.topmovieapp.data.remote.response.MovieDto

class MovieDataSource : PagingSource<Int, MovieDto>() {
    override fun getRefreshKey(state: PagingState<Int, MovieDto>): Int? {
        TODO("Not yet implemented")
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieDto> {
        TODO("Not yet implemented")
    }
}