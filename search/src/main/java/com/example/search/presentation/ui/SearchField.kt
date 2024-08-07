package com.example.search.presentation.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.example.search.R
import com.example.search.presentation.viewmodel.SearchViewModel

@Composable
fun SearchField(
    viewModel: SearchViewModel,
    onClick: () -> Unit
) {
    var query by remember { mutableStateOf("") }

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
                        query = ""
                        onClick()
                    },
                painter = painterResource(id = R.drawable.ic_close),
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onPrimary
            )
        },
        singleLine = true
    )
}