package com.example.movopfy.uiComponents.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.movopfy.uiComponents.theme.AppTheme

@Composable
fun BottomNavigationBar(
    items: List<BottomNavItem>,
    navController: NavController,
    onItemClick: (BottomNavItem) -> Unit
) {
    NavigationBar(
        containerColor = AppTheme.colorScheme.backgroundNavBar,
        modifier = Modifier.height(AppTheme.dimensions.height)
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
                            Text(text = item.label, style = AppTheme.typography.textNormal)
                        }
                    }
                },
                colors = NavigationBarItemColors(
                    selectedIconColor = AppTheme.colorScheme.brandPrimary,
                    selectedTextColor = AppTheme.colorScheme.brandPrimary,
                    selectedIndicatorColor = AppTheme.colorScheme.backgroundNavBar,
                    unselectedIconColor = AppTheme.colorScheme.textSecondary,
                    unselectedTextColor = AppTheme.colorScheme.textSecondary,
                    disabledIconColor = AppTheme.colorScheme.textSecondary,
                    disabledTextColor = AppTheme.colorScheme.textSecondary
                )
            )
        }
    }
}