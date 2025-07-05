package io.example.readcast.ui.sections

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import io.example.readcast.domain.model.Card
import io.example.readcast.ui.DurationChip

@Composable
fun BigSquareSection(
    items: List<Card>,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth().testTag("BigSquareSectionTag")
    ) {

        LazyRow(
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(items) { card ->
                BigSquareCard(card = card)
            }
        }
    }
}


@Composable
fun BigSquareCard(
    card: Card,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = Modifier.width(150.dp)
    ) {
        Card(
            shape = RoundedCornerShape(18.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
            modifier = modifier
                .width(150.dp)
                .aspectRatio(1f)
        ) {
            Box {
                AsyncImage(
                    model = card.image,
                    contentDescription = card.description,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            }
        }

        Spacer(Modifier.height(4.dp))

        Text(
            text = card.name,
            style = MaterialTheme.typography.labelMedium.copy(
                color = Color.White, fontSize = 14.sp
            ),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(vertical = 8.dp)
        ) {

            DurationChip(
                duration = card.duration,
                backgroundColor = Color.DarkGray,
                contentColor = Color.White
            )

            Spacer(modifier = Modifier.width(12.dp))


            Text(
                text = card.releaseDate ?: "Yestarday", // default value for test ui only
                style = MaterialTheme.typography.titleSmall.copy(
                    color = Color.White,
                    fontSize = 10.sp
                ),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

        }

    }

}
