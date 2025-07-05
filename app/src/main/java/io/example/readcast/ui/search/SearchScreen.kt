package io.example.readcast.ui.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import io.example.readcast.domain.model.Card
import io.example.readcast.domain.model.Section
import io.example.readcast.data.remote.UiState
import io.example.readcast.ui.header.SectionHeaderLine
import io.example.readcast.ui.home.SectionRenderer

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(vm: SearchViewModel = hiltViewModel()) {

    val query by vm.query.collectAsState()
    val uiState by vm.results.collectAsState()

    var expanded by rememberSaveable { mutableStateOf(true) }

    Column(Modifier.fillMaxSize()) {

        SearchBar(
            expanded = expanded,
            onExpandedChange = { expanded = it },

            inputField = {
                SearchBarDefaults.InputField(
                    query = query,
                    onQueryChange = vm::updateQuery,
                    onSearch = vm::updateQuery,
                    expanded = expanded,
                    onExpandedChange = { expanded = it },
                    placeholder = { Text(text = "Search…") },
                    leadingIcon = { Icon(Icons.Default.Search, null) },
                    trailingIcon = {
                        if (query.isNotBlank()) {
                            IconButton({ vm.updateQuery("") }) {
                                Icon(Icons.Default.Close, null)
                            }
                        }
                    }
                )
            },

            modifier = Modifier.fillMaxWidth(),
            tonalElevation = 4.dp
        ) {}


        when (uiState) {

            UiState.Idle -> Unit

            UiState.Loading -> CircularProgressIndicator()

            is UiState.Error -> ErrorState(
                (uiState as UiState.Error).throwable
            ) { vm.updateQuery(query) }

            is UiState.Success -> {
                val items = (uiState as UiState.Success<List<Section<Card>>>).data
                SearchResultItems(items)
            }

        }
    }
}

@Composable
fun SearchResultItems(items: List<Section<Card>>) {

    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        items(
            items,
        ) { section ->

            Spacer(Modifier.height(12.dp))

            SectionHeaderLine(headerName = section.name)

            SectionRenderer(section)

        }
    }
}


/* Error with retry */
@Composable
fun ErrorState(t: Throwable, onRetry: () -> Unit) {
    Column(
        Modifier
            .fillMaxWidth()
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Oops: ${t.localizedMessage}", color = MaterialTheme.colorScheme.error)
        Spacer(Modifier.height(16.dp))
        Button(onRetry) { Text("Retry") }
    }
}

