package com.richard_salendah.newsapp.presentation.bookmark

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import com.richard_salendah.newsapp.R
import com.richard_salendah.newsapp.core.shared.components.ArticlesListOffline
import com.richard_salendah.newsapp.core.utils.Dimens.MediumPadding1
import com.richard_salendah.newsapp.domain.entity.Article
import com.richard_salendah.newsapp.presentation.navigation.Route

@Composable
fun BookMarkScreen(
    state: BookMarkState,
    navigateToRead: (Article) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .padding(
                top = MediumPadding1,
                start = MediumPadding1,
                end = MediumPadding1,
            )
    ) {
        Text(
            text = "BookMarks",
            style = MaterialTheme.typography.displayMedium.copy(
                fontWeight = FontWeight.Bold, color = colorResource(
                    R.color.text_title
                )
            )
        )
        Spacer(modifier = Modifier.padding(MediumPadding1))

        ArticlesListOffline(articles = state.articles, onClick = {navigateToRead(it)})
    }
}