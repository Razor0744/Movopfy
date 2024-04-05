package com.example.movopfy.features.search.presentation.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.movopfy.R
import com.example.movopfy.features.search.presentation.viewmodel.SearchViewModel
import com.example.movopfy.uiComponents.components.ProgressBarLoading
import com.example.movopfy.uiComponents.navigation.Screen
import com.example.movopfy.uiComponents.theme.dimensions
import org.koin.androidx.compose.koinViewModel

@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: SearchViewModel = koinViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    var query by remember { mutableStateOf("") }

    Surface(modifier = modifier) {
        when (val state = uiState) {
            is SearchViewModel.SearchUiState.Loading -> {
                ProgressBarLoading()
            }

            is SearchViewModel.SearchUiState.Loaded -> {
                Column {
                    TextField(
                        modifier = Modifier
                            .fillMaxWidth(),
                        value = query,
                        onValueChange = {
                            query = it

                            viewModel.searchTitles(searchText = query)
                        },
                        textStyle = MaterialTheme.typography.labelSmall,
                        placeholder = {
                            Text(
                                text = stringResource(id = R.string.placeholder_search_text),
                                style = MaterialTheme.typography.labelSmall
                            )
                        },
                        leadingIcon = {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_search),
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.onPrimary
                            )
                        },
                        trailingIcon = {
                            Icon(
                                modifier = Modifier
                                    .clickable {
                                        if (query.isNotEmpty()) {
                                            query = ""

                                            viewModel.searchTitles(searchText = query)
                                        } else {
                                            navController.popBackStack()
                                        }
                                    },
                                painter = painterResource(id = R.drawable.ic_close),
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.onPrimary
                            )
                        },
                        singleLine = true
                    )

                    LazyColumn(
                        verticalArrangement = Arrangement.spacedBy(space = MaterialTheme.dimensions.lazySpace),
                        contentPadding = PaddingValues(top = MaterialTheme.dimensions.paddingTop)
                    ) {
                        items(state.list) {
                            Row(modifier = Modifier
                                .height(height = 50.dp)
                                .fillMaxWidth()
                                .clickable {
                                    navController.navigate(
                                        Screen.Details.passId(
                                            id = it.id,
                                            category = it.category
                                        )
                                    )
                                }) {
                                AsyncImage(
                                    modifier = Modifier
                                        .padding(start = MaterialTheme.dimensions.paddingStart)
                                        .height(height = 50.dp)
                                        .width(width = 50.dp)
                                        .clip(shape = MaterialTheme.shapes.small)
                                        .align(alignment = Alignment.CenterVertically),
                                    contentScale = ContentScale.FillBounds,
                                    model = it.imageUrl,
                                    contentDescription = null
                                )

                                Text(
                                    modifier = Modifier
                                        .padding(start = MaterialTheme.dimensions.paddingStart)
                                        .align(alignment = Alignment.CenterVertically),
                                    text = it.name,
                                    style = MaterialTheme.typography.labelSmall
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}