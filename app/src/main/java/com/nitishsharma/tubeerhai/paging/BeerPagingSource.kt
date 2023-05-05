package com.nitishsharma.tubeerhai.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.nitishsharma.tubeerhai.api.RetrofitInstance
import com.nitishsharma.tubeerhai.api.models.Beer

const val PAGE_SIZE = 8
const val START_PAGE = 1
const val END_PAGE = 40

class BeerPagingSource() : PagingSource<Int, Beer>() {
    override fun getRefreshKey(state: PagingState<Int, Beer>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Beer> {
        return try {
            val position = params.key ?: 1
            val response = RetrofitInstance.api.getBeersPaged(position, PAGE_SIZE)

            return LoadResult.Page(
                data = response,
                prevKey = if (position == START_PAGE) null else position - 1,
                nextKey = if (position == END_PAGE) null else position + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}