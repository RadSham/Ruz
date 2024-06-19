package com.radsham.home.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.radsham.core_api.model.EventEntity

@Composable
fun AllEvents(
    paddingValues: PaddingValues,
    eventsList: List<EventEntity>,
) {
    LazyColumn(modifier = Modifier.padding(paddingValues)) {
        items(eventsList) {
            EventCard(it)
        }
    }
}

@Composable
fun EventCard(eventEntity: EventEntity){
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
                .height(150.dp)
                .border(BorderStroke(1.dp, Color.LightGray))
            AsyncImage(
                modifier = imageModifier,
                model = eventEntity.imageUri,
                contentDescription = "eventImage"
            )
            Text(text = eventEntity.name)
            Text(text = eventEntity.category)
            Text(text = eventEntity.description)
            Text(text = eventEntity.contacts)
        }
    }
}