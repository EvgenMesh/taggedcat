package com.example.presentation.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.domain.entities.Pokemon
import com.example.presentation.models.PokemonUIState
import com.example.uikit.components.LoadingContent
import com.example.uikit.components.ShowToolbar
import kotlinx.coroutines.launch

@Composable
fun PokemonScreen(onBack:() -> Unit, viewModel: PokemonViewModel = hiltViewModel()) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    when (state) {
        is PokemonUIState.Error -> {
            ErrorContent(message = (state as PokemonUIState.Error).error) {
                viewModel.getData()
            }
        }

        PokemonUIState.Loading -> {
            LoadingContent()
        }

        is PokemonUIState.Success -> {
            ShowToolbar(text = "Pokemons", insertContent = {
                Content((state as PokemonUIState.Success).data)
            }, onBack)
        }
    }
}

@Composable
@Preview
fun TestContent() {
    Content(
        listOf(
            Pokemon("test1"),
            Pokemon("test2"),
            Pokemon("test3"),
            Pokemon("test4"),
            Pokemon("test5"),
            Pokemon("test6"),
        )
    )
}

@Composable
fun Content(data: List<Pokemon>) {
    LazyColumn(modifier = Modifier.fillMaxHeight()) {
        items(data) { item ->
            Row(
                modifier = Modifier.padding(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .background(Color.Cyan, CircleShape)
                        .size(50.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = item.name.firstOrNull().toString(),
                        color = Color.White,
                        fontSize = 26.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
                Text(
                    modifier = Modifier.padding(8.dp),
                    text = item.name,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
            }

            if (data.indexOf(item) != data.size - 1) {
                HorizontalDivider(modifier = Modifier.padding(12.dp))
            }
        }
    }
}

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun ErrorContent(
    message: String,
    onRetryClick: () -> Unit
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()
    Box(
        modifier = Modifier.fillMaxHeight(),
        contentAlignment = Alignment.BottomCenter
    ) {
        SnackbarHost(
            hostState = snackbarHostState
        )
    }

    coroutineScope.launch {
        val result = snackbarHostState.showSnackbar(
            message = message,
            actionLabel = "Retry",
            duration = SnackbarDuration.Indefinite
        )

        if (result == SnackbarResult.ActionPerformed) {
            onRetryClick()
        }
    }
}