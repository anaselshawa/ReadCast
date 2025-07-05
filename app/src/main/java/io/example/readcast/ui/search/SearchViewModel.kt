@file:OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)

package io.example.readcast.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.example.readcast.domain.model.Card
import io.example.readcast.domain.model.Section
import io.example.readcast.data.remote.UiState
import io.example.readcast.domain.usecase.SearchUseCase
import jakarta.inject.Inject
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.stateIn

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchUseCase: SearchUseCase,
) : ViewModel() {

    private val _query = MutableStateFlow("")
    val query: StateFlow<String> = _query

    fun updateQuery(newValue: String) {
        _query.value = newValue
    }

    /** Search results, re-queried 200 ms after user stops typing */
    val results: StateFlow<UiState<List<Section<Card>>>> = _query
        .debounce(200)
        .distinctUntilChanged()
        .flatMapLatest { q ->
            if (q.isBlank())
                flowOf<UiState<List<Section<Card>>>>(UiState.Idle)
            else {
                flow {
                    emit(UiState.Loading)
                    try {
                        val data = searchUseCase.invoke(q)
                        emit(UiState.Success(data))
                    } catch (t: Throwable) {
                        emit(UiState.Error(t))
                    }
                }
            }
        }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), UiState.Idle)
}


