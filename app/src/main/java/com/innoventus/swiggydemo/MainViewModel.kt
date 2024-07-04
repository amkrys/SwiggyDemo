package com.innoventus.swiggydemo

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn

class MainViewModel: ViewModel() {

    val query = MutableLiveData<String>()

    /*val flow = Pager(
        PagingConfig(pageSize = 20)
    ) {
        MovieDataSource(RetrofitBuilder.getInstance(), query.value ?: "")
    }.flow
        .cachedIn(viewModelScope)*/

}