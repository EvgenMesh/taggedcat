package com.example.main.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.entities.Result
import com.example.domain.usecase.GetCatsUseCase
import com.example.main.mapper.mapToUIModel
import com.example.main.model.CatUIModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CatViewModel @Inject constructor(private val getCatUseCase: GetCatsUseCase) : ViewModel() {
    sealed class UIState {
        data class Success(val data: List<CatUIModel>, val retryClick: () -> Unit) : UIState()
        data class Error(val retryClick: () -> Unit) : UIState()
        data object Loading : UIState()
    }

    private val _uiState = MutableStateFlow<UIState>(UIState.Loading)
    val uiState = _uiState.asStateFlow()

    init {
        getData()
    }

    private fun getData() {
        getCatUseCase.invoke().onEach {
            when (it) {
                is Result.Failure -> {
                    _uiState.value = UIState.Error {
                        getData()
                    }
                }

                is Result.Success -> {
                    _uiState.value = UIState.Success(it.value.mapToUIModel()) {
                        getData()
                    }
                }
            }
        }.launchIn(viewModelScope)
    }
}