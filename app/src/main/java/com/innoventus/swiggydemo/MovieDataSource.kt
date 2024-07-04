package com.innoventus.swiggydemo

import androidx.paging.PagingSource
import androidx.paging.PagingState

/*
class MovieDataSource(
    val backend: ApiService,
    val query: String
) : PagingSource<Int, Search>() {
    override suspend fun load(
        params: LoadParams<Int>
    ): LoadResult<Int, Search> {
        try {
            val nextPageNumber = params.key ?: 1
            val response = backend.getMoviesList(query, nextPageNumber)
            return LoadResult.Page(
                data = response.body()?.Search ?: arrayListOf(),
                prevKey = null,
                nextKey = null
            )
        } catch (e: Exception) {
            e.printStackTrace()
            return LoadResult.Error(e.cause ?: Throwable())
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Search>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}*/
