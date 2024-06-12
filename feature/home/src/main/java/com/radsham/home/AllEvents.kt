package com.radsham.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import com.radsham.core_api.model.EventEntity

@Composable
fun AllEvents(
    paddingValues: PaddingValues,
    eventsList: List<EventEntity>,
    showTopBar: MutableState<Boolean>,
) {

    LazyColumn(modifier = Modifier.padding(paddingValues)) {
        showTopBar.value = true
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
            .padding(2.dp)
            .shadow(
                2.dp,
                ambientColor = MaterialTheme.colorScheme.primary,
                spotColor = MaterialTheme.colorScheme.secondary
            ),
        shape = RoundedCornerShape(3.dp),
    ){
        Column {
            Text(text = eventEntity.name)
            Text(text = eventEntity.category)
            Text(text = eventEntity.description)
            Text(text = eventEntity.contacts)
        }
    }
}