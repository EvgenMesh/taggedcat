package com.example.presentation.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.entities.Result
import com.example.domain.usecase.GetCatsUseCase
import com.example.presentation.mapper.mapToUIModel
import com.example.presentation.model.CatUIModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CatViewModule(private val getCatUseCase: GetCatsUseCase) : ViewModel() {
    sealed class UIState {
        data class Success(val data: List<CatUIModel>) : UIState()
        data class Error(val error: String?, val retryClick: () -> Unit) : UIState()
        data object Loading : UIState()
    }

    private val _uiState = MutableStateFlow<UIState>(UIState.Loading)
    val uiState = _uiState.asStateFlow()

    init {
        getData()
    }

    private fun getData() {
        viewModelScope.launch {
            when (val result = getCatUseCase.invoke()) {
                is Result.Failure -> {
                    _uiState.value = UIState.Error(result.error.message) {
                        getData()
                    }
                }
                is Result.Success -> {
                    _uiState.value = UIState.Success(result.value.mapToUIModel())
                }
            }
        }
    }
}