package com.asalazar.geobanderas.presentation.ui.countries

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.asalazar.geobanderas.domain.Country
import org.koin.androidx.compose.koinViewModel

@Composable
fun CountriesScreen(
    contriesViewModel: CountriesViewModel = koinViewModel()
) {

    val uiState by contriesViewModel.uiState.collectAsStateWithLifecycle()

    Box(Modifier.fillMaxSize().safeDrawingPadding()) {
        when (val state = uiState) {
            is UiState.Loading -> Text("Loading...")
            is UiState.Error -> Text(state.throwable.message.orEmpty())
            is UiState.Success -> CountriesScreenContent(state.countries)
        }
    }
}

@Composable
fun CountriesScreenContent(
    countries: List<Country>,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier) {
        items(countries) { country ->
            CountryItem(country)
        }
    }
}

@Composable
fun CountryItem(country: Country, modifier: Modifier = Modifier) {
    Text(country.name.official, modifier = modifier)
}
