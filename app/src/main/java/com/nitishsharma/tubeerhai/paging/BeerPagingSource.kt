package com.nitishsharma.tubeerhai.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.nitishsharma.tubeerhai.api.RetrofitInstance
import com.nitishsharma.tubeerhai.api.models.Beer

const val PAGE_SIZE = 8
const val START_PAGE = 1
const val END_PAGE = 40

class BeerPagingSource() : PagingSource<Int, Beer>() {
    //refresh key will help in consecutive calls to the api and managing the keys, even if the track is lost
    override fun getRefreshKey(state: PagingState<Int, Beer>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Beer> {
        return try {
            val position = params.key ?: 1 //if value of position is null return 1 and load page 1
            val response = RetrofitInstance.api.getBeersPaged(
                position,
                PAGE_SIZE
            ) //call to api for getting the paged info

            return LoadResult.Page(
                data = response,
                prevKey = if (position == START_PAGE) null else position - 1, //defining start page
                nextKey = if (position == END_PAGE) null else position + 1 //defining end page
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}