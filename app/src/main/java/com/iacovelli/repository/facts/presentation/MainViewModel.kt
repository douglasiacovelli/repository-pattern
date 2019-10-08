package com.iacovelli.repository.facts.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.iacovelli.repository.facts.data.CatFactCacheService
import com.iacovelli.repository.facts.data.CatFactRemoteService
import com.iacovelli.repository.facts.domain.Fact
import kotlinx.coroutines.launch

class MainViewModel(
    private val remoteService: CatFactRemoteService,
    private val cacheService: CatFactCacheService
) : ViewModel() {

    val cats = MutableLiveData<Fact>()

    fun fetchData() {
        viewModelScope.launch {
            try {
                val result = remoteService.getFacts().toModel()
                cats.value = result.random()
            } catch(exception: Exception) {
                val result = cacheService.getFacts().random()
                cats.value = Fact(result)
            }
        }
    }

    @Suppress("UNCHECKED_CAST")
    class Factory(
        private val remoteService: CatFactRemoteService,
        private val cacheService: CatFactCacheService
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return MainViewModel(remoteService, cacheService) as T
        }
    }
}