package io.example.readcast.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun LoadingScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = androidx.compose.ui.Alignment.Center
    ) {
        CircularProgressIndicator(
            modifier = Modifier.size(36.dp)
        )
    }
}

@Composable
fun ErrorScreen(message: String, onRetry: () -> Unit) {
    Text(text = message)
    Button(onClick = onRetry) {
        Text("Retry")
    }
}

@Composable
fun LoadingItem() {
    CircularProgressIndicator()
}

@Composable
fun ErrorItem(message: String, onRetry: () -> Unit) {
    Text(text = message)
    Button(onClick = onRetry) {
        Text("Retry")
    }
}
