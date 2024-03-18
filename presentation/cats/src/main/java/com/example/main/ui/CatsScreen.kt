package com.example.main.ui

import android.text.Html
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.main.R
import com.example.main.model.CatUIModel
import com.example.uikit.components.LoadingContent
import com.example.uikit.components.ShowSnackBar
import com.example.uikit.components.ShowToolbar

@Composable
fun CatsScreen(onBack:() -> Unit, viewModel: CatViewModel = hiltViewModel()) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    when (state) {
        is CatViewModel.UIState.Error -> {
            ShowSnackBar(text = stringResource(R.string.error_info))
        }

        CatViewModel.UIState.Loading -> {
            LoadingContent()
        }

        is CatViewModel.UIState.Success -> {
            CatsContent(data = (state as CatViewModel.UIState.Success).data, onBack)
        }
    }
}

@Composable
fun CatsContent(data: List<CatUIModel>, onBack: () -> Unit) {
    ShowToolbar(text = "Cats", insertContent = {
        Content(data = data)
    }, onBack)
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun Content(data: List<CatUIModel>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
    ) {
        items(data) { item ->
            Row(
                modifier = Modifier.padding(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                GlideImage(
                    model = item.mediaUrl,
                    contentDescription = null
                )
                Text(
                    modifier = Modifier.padding(12.dp),
                    text = Html.fromHtml(item.description, Html.FROM_HTML_MODE_LEGACY).toString(),
                    color = Color.Black,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            if (data.indexOf(item) != data.size - 1) {
                HorizontalDivider(modifier = Modifier.padding(12.dp))
            }
        }
    }
}
