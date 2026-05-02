package com.asalazar.geobanderas.presentation.ui.countries

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.asalazar.geobanderas.data.CountryRepository
import com.asalazar.geobanderas.domain.Country
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class CountriesViewModel(
    private val countryRepository: CountryRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState>(UiState.Loading)

    val uiState = _uiState
        .onStart { fetchCountries() }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), UiState.Loading)


    private fun fetchCountries() {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            try {
                val countries = countryRepository.getAmericaCountries()
                _uiState.value = UiState.Success(countries)
            } catch (e: Exception) {
                _uiState.value = UiState.Error(e)
            }
        }
    }

}

sealed class UiState {
    object Loading : UiState()
    data class Error(val throwable: Throwable) : UiState()
    data class Success(val countries: List<Country>) : UiState()
}
