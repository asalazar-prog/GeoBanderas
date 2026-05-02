package com.asalazar.geobanderas.presentation.ui.countries

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import com.asalazar.geobanderas.domain.Country
import org.koin.androidx.compose.koinViewModel

@Composable
fun CountriesScreen(
    contriesViewModel: CountriesViewModel = koinViewModel()
) {

    val uiState by contriesViewModel.uiState.collectAsStateWithLifecycle()

    Surface(
        Modifier
            .fillMaxSize()
            .safeDrawingPadding()
    ) {
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
    LazyColumn(modifier, verticalArrangement = Arrangement.spacedBy(8.dp)) {
        items(countries) { country ->
            CountryItem(
                country, modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            )
        }
    }
}

@Composable
fun CountryItem(country: Country, modifier: Modifier = Modifier) {
    Card(modifier = modifier, colors = CardDefaults.cardColors(containerColor = Color.White)) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            AsyncImage(
                model = country.flags.pngUrl,
                contentDescription = country.flags.contentDescription,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(2 / 1f)
            )
            Column {
                Text(
                    country.name.official,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )
                Text(country.capital.first(), style = MaterialTheme.typography.titleMedium)
            }


            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = MaterialTheme.colorScheme.primaryContainer,
                        shape = MaterialTheme.shapes.medium
                    )
            ) {
                Row(
                    modifier = Modifier.padding(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(Icons.Filled.LocationOn, contentDescription = "Location")
                    Text(
                        country.latlng.joinToString(","),
                        style = MaterialTheme.typography.labelSmall
                    )
                }
            }

        }

    }
}
