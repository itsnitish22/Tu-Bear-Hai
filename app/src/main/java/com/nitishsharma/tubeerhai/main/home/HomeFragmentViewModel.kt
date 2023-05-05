package com.nitishsharma.tubeerhai.main.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.nitishsharma.tubeerhai.paging.BeerRepository

class HomeFragmentViewModel : ViewModel() {
    val listOfBeers = BeerRepository().getBeers().cachedIn(viewModelScope)
}