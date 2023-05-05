package com.nitishsharma.tubeerhai.paging

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData

class BeerRepository {
    fun getBeers() = Pager(
        config = PagingConfig(pageSize = 5, maxSize = 20),
        pagingSourceFactory = { BeerPagingSource() }
    ).liveData
}