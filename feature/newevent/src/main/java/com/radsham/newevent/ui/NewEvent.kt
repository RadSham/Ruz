package com.radsham.newevent.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.radsham.newevent.R

@Preview
@Composable
fun NewEvent() {
    Column(
        modifier = Modifier
            .padding(20.dp)
    ) {
        var nameText by remember { mutableStateOf("Simple event") }
        var categoryText by remember { mutableStateOf("Funny category") }
        var descriptionText by remember { mutableStateOf("Laconic description") }
        var contactsText by remember { mutableStateOf("Real contacts") }

        val imageModifier = Modifier
            .fillMaxWidth()
            .size(150.dp)
            .border(BorderStroke(1.dp, Color.Black))
            .background(Color.Transparent)

        Image(
            modifier = imageModifier,
            painter = painterResource(id = R.drawable.baseline_image_search_24),
            contentDescription = stringResource(id = R.string.empty_image),
        )
        Row {
            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = nameText,
                onValueChange = { nameText = it },
                label = { Text("Name") },
                trailingIcon = {
                    if (nameText.isNotEmpty()) {
                        IconButton(onClick = { nameText = "" }) {
                            Icon(
                                imageVector = Icons.Outlined.Close,
                                contentDescription = null
                            )
                        }
                    }
                }
            )
        }
        Row {
            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = categoryText,
                onValueChange = { categoryText = it },
                label = { Text("Category") },
                trailingIcon = {
                    if (categoryText.isNotEmpty()) {
                        IconButton(onClick = { categoryText = "" }) {
                            Icon(
                                imageVector = Icons.Outlined.Close,
                                contentDescription = null
                            )
                        }
                    }
                }
            )
        }
        Row {
            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = descriptionText,
                onValueChange = { descriptionText = it },
                label = { Text("Description") },
                trailingIcon = {
                    if (descriptionText.isNotEmpty()) {
                        IconButton(onClick = { descriptionText = "" }) {
                            Icon(
                                imageVector = Icons.Outlined.Close,
                                contentDescription = null
                            )
                        }
                    }
                }
            )
        }
        Row {
            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = contactsText,
                onValueChange = { contactsText = it },
                label = { Text("Contacts") },
                trailingIcon = {
                    if (contactsText.isNotEmpty()) {
                        IconButton(onClick = { contactsText = "" }) {
                            Icon(
                                imageVector = Icons.Outlined.Close,
                                contentDescription = null
                            )
                        }
                    }
                }
            )
        }
    }
}
