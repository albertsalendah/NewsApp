package com.richard_salendah.newsapp.core.shared.components

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.richard_salendah.newsapp.R
import com.richard_salendah.newsapp.core.theme.AppTheme
import com.richard_salendah.newsapp.core.utils.Dimens.ArticleCardSize
import com.richard_salendah.newsapp.core.utils.Dimens.ExtraSmallPadding
import com.richard_salendah.newsapp.core.utils.Dimens.MediumPadding1

fun Modifier.shimmerEffect() = composed {
    val transition = rememberInfiniteTransition()
    val alpha = transition.animateFloat(
        initialValue = 0.2f,
        targetValue = 0.9f,
        animationSpec = infiniteRepeatable(
            animation = tween(delayMillis = 1000),
            repeatMode = RepeatMode.Reverse
        )
    ).value
    background(color = colorResource(R.color.shimmer).copy(alpha = alpha))
}

@Composable
fun ArticleShimmer(modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .size(ArticleCardSize)
                .clip(MaterialTheme.shapes.medium)
                .shimmerEffect(),
        )
        Spacer(modifier = Modifier.height(MediumPadding1))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = MediumPadding1)
                .height(30.dp)
                .shimmerEffect()
        )
        Column(
            verticalArrangement = Arrangement.SpaceAround,
            modifier = Modifier
                .padding(horizontal = ExtraSmallPadding)
                .height(ArticleCardSize)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = MediumPadding1)
                    .height(30.dp)
                    .shimmerEffect()
            )

            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.5f)
                        .padding(horizontal = MediumPadding1)
                        .height(15.dp)
                        .shimmerEffect()
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun ArticleShimmerPrev(){
    AppTheme {
        ArticleShimmer()
    }
}