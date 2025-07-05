package io.example.readcast.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import io.example.readcast.domain.usecase.HomeSectionsUseCase
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    getSectionsUseCase: HomeSectionsUseCase,
) : ViewModel() {

    val sections = getSectionsUseCase().cachedIn(viewModelScope)
}