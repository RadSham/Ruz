package com.radsham.eventdetails.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.radsham.core_api.model.EventEntity
import com.radsham.eventdetails.R


@Composable
fun EventDetails(paddingValues: PaddingValues, eventEntity: EventEntity) {
    Box(Modifier.padding(paddingValues)) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp)
                .shadow(
                    2.dp,
                    ambientColor = MaterialTheme.colorScheme.primary,
                    spotColor = MaterialTheme.colorScheme.secondary
                ),
            shape = RoundedCornerShape(3.dp),
            border = BorderStroke(1.dp, Color.Black)
        ) {
            Column(modifier = Modifier.padding(5.dp)) {
                val imageModifier = Modifier
                    .fillMaxWidth()
                    .size(150.dp)
                    .border(BorderStroke(1.dp, Color.LightGray))
                if (eventEntity.imageUri == "")
                    Image(
                        modifier = imageModifier,
                        painter = painterResource(R.drawable.baseline_image_search_24),
                        contentScale = ContentScale.Fit,
                        contentDescription = stringResource(id = R.string.event_image)
                    )
                else
                    AsyncImage(
                        modifier = imageModifier,
                        model = eventEntity.imageUri,
                        contentDescription = stringResource(id = R.string.event_image)
                    )
                Row {
                    Text(text = eventEntity.name)
                    Text(text = eventEntity.name)
                }
                Text(text = eventEntity.location)
                Text(text = eventEntity.category)
                Text(text = eventEntity.description)
                Text(text = eventEntity.contacts)
            }
        }
    }
}
