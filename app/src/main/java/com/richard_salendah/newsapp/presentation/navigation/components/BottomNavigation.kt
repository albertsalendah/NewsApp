package com.richard_salendah.newsapp.presentation.navigation.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.richard_salendah.newsapp.R
import com.richard_salendah.newsapp.core.theme.AppTheme
import com.richard_salendah.newsapp.core.utils.Dimens.ExtraSmallPadding2
import com.richard_salendah.newsapp.core.utils.Dimens.IconSize

@Composable
fun BottomNavigation(
    items: List<BottomNavigationItem>,
    selectedItemIndex: Int,
    onItemClick: (Int) -> Unit
) {
    NavigationBar(
        modifier = Modifier.fillMaxWidth(),
        containerColor = MaterialTheme.colorScheme.background,
        tonalElevation = 10.dp
    ) {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = selectedItemIndex == index,
                onClick = { onItemClick(index) },
                icon = {
                    Column(horizontalAlignment = CenterHorizontally) {
                        Icon(
                            painter = painterResource(id = item.icon),
                            contentDescription = null,
                            modifier = Modifier.size(IconSize)
                        )
                        Spacer(modifier = Modifier.height(ExtraSmallPadding2))
                        Text(
                            text = item.label,
                            style = MaterialTheme.typography.labelSmall
                        )
                    }
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = MaterialTheme.colorScheme.primary,
                    selectedTextColor = MaterialTheme.colorScheme.primary,
                    unselectedIconColor = colorResource(R.color.body),//MaterialTheme.colorScheme.onSurfaceVariant,
                    unselectedTextColor = colorResource(R.color.body),
                    indicatorColor = MaterialTheme.colorScheme.background

                )
            )
        }
    }
}


data class BottomNavigationItem(
    @DrawableRes val icon: Int,
    val label: String,
)

@Preview(showBackground = true)
@Composable
fun BottomNavigationPreview() {
    AppTheme {
        BottomNavigation(items = listOf(
            BottomNavigationItem(icon = R.drawable.ic_home, label = "Home"),
            BottomNavigationItem(icon = R.drawable.ic_search, label = "Search"),
            BottomNavigationItem(icon = R.drawable.ic_bookmark, label = "Bookmark"),
        ), selectedItemIndex = 0, onItemClick = {})
    }
}