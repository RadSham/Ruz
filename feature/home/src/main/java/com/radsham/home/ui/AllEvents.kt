package com.radsham.home.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.radsham.core_api.NavScreen
import com.radsham.core_api.model.EventEntity
import com.radsham.home.R

@Composable
fun AllEvents(
    paddingValues: PaddingValues,
    navController: NavHostController,
    eventsList: List<EventEntity>,
    queryName: MutableState<String>,
    categoriesSelectedList: List<String>
) {
    val filteredList =
        eventsList.filter {
            if (categoriesSelectedList.isEmpty()) {
                it.name.contains(queryName.value) || categoriesSelectedList.contains(it.category)
            } else{
                it.name.contains(queryName.value) && categoriesSelectedList.contains(it.category)
            }
        }
    LazyColumn(modifier = Modifier.padding(paddingValues)) {
        items(filteredList) {
            EventCard(navController, it)
        }
    }
}

@Composable
fun EventCard(navController: NavHostController, eventEntity: EventEntity) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)
            .shadow(
                2.dp,
                ambientColor = MaterialTheme.colorScheme.primary,
                spotColor = MaterialTheme.colorScheme.secondary
            )
            .clickable { navController.navigate(NavScreen.EVENT_DETAILS_SCREEN + "/" + eventEntity.id) },
        shape = RoundedCornerShape(3.dp),
        border = BorderStroke(1.dp, Color.Black)
    ) {
        Column(modifier = Modifier.padding(5.dp)) {
            val imageModifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
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
            Text(text = eventEntity.name, maxLines = 2)
            Text(text = eventEntity.location, maxLines = 2)
        }
    }
}