package com.richard_salendah.newsapp.presentation.Read

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.richard_salendah.newsapp.R
import com.richard_salendah.newsapp.core.theme.AppTheme
import com.richard_salendah.newsapp.core.utils.Dimens.ArticleImageHeight
import com.richard_salendah.newsapp.core.utils.Dimens.ExtraSmallPadding2
import com.richard_salendah.newsapp.core.utils.Dimens.MediumPadding1
import com.richard_salendah.newsapp.core.utils.Dimens.SmallIconSize
import com.richard_salendah.newsapp.domain.entity.Article
import com.richard_salendah.newsapp.presentation.Read.components.ReadBar

@Composable
fun ReadScreen(
    isBookmarked: Boolean,
    article: Article,
    event: (ReadEvent) -> Unit,
    navigate: () -> Unit
) {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
    ) {
        ReadBar(
            onBackClick = navigate,
            onBrowserClick = {
                Intent(Intent.ACTION_VIEW).also {
                    it.data = Uri.parse(article.url)
                    if (it.resolveActivity(context.packageManager) != null) {
                        context.startActivity(it)
                    }
                }
            },
            onShareClick = {
                Intent(Intent.ACTION_SEND).also {
                    it.putExtra(Intent.EXTRA_TEXT, article.url)
                    it.type = "text/plain"
                    if (it.resolveActivity(context.packageManager) != null) {
                        context.startActivity(it)
                    }
                }
            },
            onBookmarkClick = {
                event(ReadEvent.InsertDeleteArticle(article = article))
            },
            isBookmarked = isBookmarked
        )

        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(
                start = MediumPadding1,
                end = MediumPadding1,
                top = MediumPadding1,
            )
        ) {
            item {
                AsyncImage(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(ArticleImageHeight)
                        .clip(MaterialTheme.shapes.medium),
                    model = ImageRequest.Builder(context).data(article.urlToImage).build(),
                    contentDescription = null,
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.width(MediumPadding1))
                Text(
                    text = article.title ?: "",
                    style = MaterialTheme.typography.displaySmall,
                    color = colorResource(R.color.text_title),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = article.description ?: "",
                    style = MaterialTheme.typography.labelMedium,
                    color = colorResource(id = R.color.body),
                )
                Text(
                    text = article.content ?: "",
                    style = MaterialTheme.typography.bodyMedium,
                    color = colorResource(R.color.body),
                )
                Spacer(modifier = Modifier.width(MediumPadding1))
                Text(
                    text = "Source : ${article.source?.name ?: ""}",
                    style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
                    color = colorResource(id = R.color.body),
                )
                Spacer(modifier = Modifier.width(ExtraSmallPadding2))
                Text(
                    text = "Author : ${article.author ?: ""}",
                    style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
                    color = colorResource(id = R.color.body),
                )
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
                    )
                }
                Text(
                    "Url : ${article.url ?: ""}",
                    style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
                    color = colorResource(id = R.color.body),
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ReadScreenPreview() {
    AppTheme {
//        ReadScreen(
//
//            article = Article(
//                author = "AJ Dellinger",
//                description = "The tech company turned Bitcoin repository is changing its name.",
//                publishedAt = "2025-02-06T13:45:12Z",
//                source = Source(id = "", name = "ABC"),
//                title = "MicroStrategy Says Drop the Micro, It’s Cleaner",
//                url = "https://gizmodo.com/microstrategy-says-drop-the-micro-its-cleaner-2000559905",
//                urlToImage = "https://gizmodo.com/app/uploads/2025/02/GettyImages-2039371693.jpg",
//                content = "Michael Saylor is apparently done thinking small. The founder and CEO of MicroStrategy announced Wednesday that his company would be dropping the “Micro” from its name, instead operating as Strategy.… [+2833 chars]",
//            ),
//            event = {},
//            navigate = {}
//        )
    }
}
