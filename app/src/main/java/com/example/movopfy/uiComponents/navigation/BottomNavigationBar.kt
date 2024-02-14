package com.example.movopfy.uiComponents.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.movopfy.uiComponents.theme.BackgroundNavBar

@Composable
fun BottomNavigationBar(
    items: List<BottomNavItem>,
    navController: NavController,
    onItemClick: (BottomNavItem) -> Unit
) {
    NavigationBar(
        containerColor = BackgroundNavBar,
        modifier = Modifier.height(height = 52.dp)
    ) {
        val backStackEntry = navController.currentBackStackEntryAsState()
        items.forEachIndexed { _, item ->
            val selected = item.route == backStackEntry.value?.destination?.route
            NavigationBarItem(
                selected = selected,
                onClick = { onItemClick(item) },
                icon = {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(imageVector = item.icon, contentDescription = null)
                        if (selected) {
                            Text(text = item.label, style = MaterialTheme.typography.labelSmall)
                        }
                    }
                },
                colors = NavigationBarItemColors(
                    selectedIconColor = MaterialTheme.colorScheme.primary,
                    selectedTextColor = MaterialTheme.colorScheme.primary,
                    selectedIndicatorColor = BackgroundNavBar,
                    unselectedIconColor = MaterialTheme.colorScheme.onSecondary,
                    unselectedTextColor = MaterialTheme.colorScheme.onSecondary,
                    disabledIconColor = MaterialTheme.colorScheme.onSecondary,
                    disabledTextColor = MaterialTheme.colorScheme.onSecondary
                )
            )
        }
    }
}