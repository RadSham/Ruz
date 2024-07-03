package com.radsham.eventdetails.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.radsham.core_api.listener.UserAuthorizedListener
import com.radsham.core_api.model.EventEntity
import com.radsham.core_api.model.User
import com.radsham.eventdetails.R


@Composable
fun EventDetails(
    paddingValues: PaddingValues,
    eventEntity: EventEntity,
    currentUser: User,
    userAuthorizedListener: UserAuthorizedListener,
) {
    var participantCount by remember { mutableIntStateOf(0) }
    var participantButtonBoolean by remember { mutableStateOf(false) }
    participantCount = eventEntity.participants.size
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
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = stringResource(R.string.name))
                    Text(text = eventEntity.name)
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = stringResource(R.string.location))
                    Text(text = eventEntity.location)
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = stringResource(R.string.category))
                    Text(text = eventEntity.category)
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = stringResource(R.string.description))
                    Text(text = eventEntity.description)
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = stringResource(R.string.contacts))
                    Text(text = eventEntity.contacts)
                }
                Row(
                    Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "Participants: $participantCount")
                    if (!participantButtonBoolean) {
                        Button(onClick = {
                            if (isAuthUser(currentUser)) {
                                /*participantCount++
                                participantButtonBoolean = !participantButtonBoolean*/
                                userAuthorizedListener.onIamIn()
                            } else {
                                userAuthorizedListener.onFailure()
                            }
                        }) {
                            Text("I'm in")
                        }
                    } else {
                        Button(onClick = {
                            if (isAuthUser(currentUser)) {
                                participantCount--
                                participantButtonBoolean = !participantButtonBoolean
                                userAuthorizedListener.onIamOut()
                            } else {
                                userAuthorizedListener.onFailure()
                            }
                        }) {
                            Text("I'm out")
                        }
                    }
                }
            }
        }
    }
}

fun isAuthUser(currentUser: User): Boolean {
    return currentUser.uid != "null"
}


