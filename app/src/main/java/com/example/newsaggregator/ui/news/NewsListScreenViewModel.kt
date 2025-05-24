package com.example.newsaggregator.ui.news

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsaggregator.data.db.News
import com.example.newsaggregator.domain.use_case.GetNewsUseCase
import com.example.newsaggregator.ui.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsListScreenViewModel @Inject constructor(
    private val getNewsUseCase: GetNewsUseCase
): ViewModel() {

    private val _newsState = MutableStateFlow<UiState<List<News>>>(UiState.Idle)
    val newsState: StateFlow<UiState<List<News>>> get() = _newsState

    init {
        loadNews()
    }

    fun loadNews() {
        _newsState.value = UiState.Loading
        viewModelScope.launch {
            try {
                val result = getNewsUseCase()
                _newsState.value = UiState.Success(result)
            } catch (e: Exception) {
                _newsState.value = UiState.Error(message = "Error: ${e.message}")
            }
        }
    }
}