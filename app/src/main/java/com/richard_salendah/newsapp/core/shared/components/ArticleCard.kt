package com.richard_salendah.newsapp.core.shared.components

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.richard_salendah.newsapp.R
import com.richard_salendah.newsapp.core.theme.AppTheme
import com.richard_salendah.newsapp.core.utils.Dimens
import com.richard_salendah.newsapp.core.utils.Dimens.ArticleCardSize
import com.richard_salendah.newsapp.core.utils.Dimens.ExtraSmallPadding
import com.richard_salendah.newsapp.core.utils.Dimens.ExtraSmallPadding2
import com.richard_salendah.newsapp.core.utils.Dimens.MediumPadding1
import com.richard_salendah.newsapp.core.utils.Dimens.SmallIconSize
import com.richard_salendah.newsapp.domain.entity.Article
import com.richard_salendah.newsapp.domain.entity.Source

@Composable
fun ArticleCard(modifier: Modifier = Modifier, article: Article, onClick: () -> Unit) {
    val context = LocalContext.current
    Card(
        colors = CardDefaults.cardColors(colorResource(R.color.input_background)),
        modifier = modifier
            .fillMaxWidth()
            .clickable { onClick() },
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Column(modifier = Modifier.padding(bottom = 8.dp)) {
            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                model = ImageRequest.Builder(context).data(article.urlToImage).build(),
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
            Text(
                modifier = Modifier.padding(horizontal = MediumPadding1),
                text = article.title ?: "",
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                modifier = Modifier.padding(MediumPadding1),
                text = article.description ?: "",
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Column(
                modifier = Modifier
                    .padding(horizontal = MediumPadding1)
                    .fillMaxWidth(),
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        text = article.source?.name ?: "",
                        style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
                        color = colorResource(id = R.color.body),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Spacer(modifier = Modifier.width(ExtraSmallPadding2))
                    Text(
                        text = article.author ?: "",
                        style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
                        color = colorResource(id = R.color.body),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_time),
                        contentDescription = null,
                        modifier = Modifier.size(SmallIconSize),
                        tint = colorResource(R.color.body)

                    )
                    Spacer(modifier = Modifier.width(ExtraSmallPadding2))
                    Text(
                        article.publishedAt ?: "",
                        style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
                        color = colorResource(id = R.color.body),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }

            }
        }
    }
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun ArticleCardPreview() {
    AppTheme {
        ArticleCard(
            article = Article(
                author = "AJ Dellinger",
                description = "The tech company turned Bitcoin repository is changing its name.",
                publishedAt = "2025-02-06T13:45:12Z",
                source = Source(id = "", name = "ABC"),
                title = "MicroStrategy Says Drop the Micro, It’s Cleaner",
                url = "https://gizmodo.com/microstrategy-says-drop-the-micro-its-cleaner-2000559905",
                urlToImage = "https://gizmodo.com/app/uploads/2025/02/GettyImages-2039371693.jpg",
                content = "Michael Saylor is apparently done thinking small. The founder and CEO of MicroStrategy announced Wednesday that his company would be dropping the “Micro” from its name, instead operating as Strategy.… [+2833 chars]",
            )
        ) {
        }
    }
}