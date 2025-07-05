package io.example.readcast.ui.sections

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import io.example.readcast.R
import io.example.readcast.domain.model.Card
import io.example.readcast.ui.DurationChip

private val GridCardHeight = 120.dp

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun GridSection(
    items: List<Card>,
    modifier: Modifier = Modifier,
) {

    Column(modifier) {

        Spacer(Modifier.height(8.dp))

        LazyHorizontalGrid(
            rows = GridCells.Fixed(2),
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier
                .testTag("GridSectionTag")
                .fillMaxWidth()
                // height = 2 * itemHeight + vertical spacing.
                .height(GridCardHeight * 2 + 12.dp)
        ) {
            items(items) { card ->
                GridCard(card)
            }
        }

    }
}

@Composable
fun GridCard(card: Card) {

    Row(
        modifier = Modifier.width(300.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Card(
            modifier = Modifier
                .width(100.dp)
                .fillMaxHeight(),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color.Transparent)
        ) {
            AsyncImage(
                model = card.image,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(GridCardHeight)
            )
        }

        Spacer(Modifier.width(12.dp))

        Column(
            modifier = Modifier
                .fillMaxHeight()
                .weight(1f),
            verticalArrangement = Arrangement.SpaceBetween
        ) {

            Column {
                // author

                Text(
                    text = (if (card.authorName.isNullOrBlank()) {
                        card.releaseDate ?: ""
                    } else {
                        card.authorName
                    }).toString(),
                    style = MaterialTheme.typography.labelSmall.copy(
                        color = Color.White,
                    ),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )


                // title
                Text(
                    text = card.name,
                    maxLines = 1,
                    style = MaterialTheme.typography.titleMedium.copy(
                        color = Color.White,
                    ),
                    overflow = TextOverflow.Ellipsis
                )

                // description
                card.description?.let {
                    Text(
                        text = it,
                        maxLines = 2,
                        style = MaterialTheme.typography.bodySmall.copy(
                            color = Color.White,
                        ),
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }

            Row {

                DurationChip(card.duration)

                Spacer(modifier = Modifier.weight(1f))

                Icon(
                    painter = painterResource(id = R.drawable.more_horiz),
                    contentDescription = "More icon",
                    tint = Color.White
                )

                Spacer(modifier = Modifier.width(8.dp))

                Icon(
                    painter = painterResource(id = R.drawable.outline_forms_add_on),
                    contentDescription = "Add icon",
                    tint = Color.Gray
                )
            }

        }

    }

}



