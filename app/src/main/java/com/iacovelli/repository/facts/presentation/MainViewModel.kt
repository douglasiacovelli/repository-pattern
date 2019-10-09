package com.iacovelli.repository.facts.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.iacovelli.repository.facts.data.Repository
import com.iacovelli.repository.facts.domain.Fact
import kotlinx.coroutines.launch

class MainViewModel(
    private val repository: Repository
) : ViewModel() {

    val cats = MutableLiveData<Fact>()

    fun fetchData() {
        viewModelScope.launch {
            cats.value = repository.getFact()
        }
    }

    @Suppress("UNCHECKED_CAST")
    class Factory(
        private val repository: Repository
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return MainViewModel(repository) as T
        }
    }
}