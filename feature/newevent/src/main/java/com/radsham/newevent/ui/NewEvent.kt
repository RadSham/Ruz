package com.radsham.newevent.ui

import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.radsham.core_api.NavScreen
import com.radsham.core_api.listener.EventCreateListener
import com.radsham.core_api.model.EventEntity
import com.radsham.newevent.R
import com.radsham.newevent.viewmodel.NewEventViewModel


@Composable
fun NewEvent(paddingValues: PaddingValues, navController: NavHostController) {
    Box(modifier = Modifier.padding(paddingValues)) {
        val viewModel: NewEventViewModel = hiltViewModel()
        Column(
            modifier = Modifier.padding(20.dp)
        ) {
            var selectedImagesUri: Uri by remember { mutableStateOf("".toUri()) }
            var nameText by remember { mutableStateOf("") }
            var locationText by remember { mutableStateOf("") }
            val categoryText = remember { mutableStateMapOf<String, String>() }
            var descriptionText by remember { mutableStateOf("") }
            var contactsText by remember { mutableStateOf("") }

            var isErrorName by remember { mutableStateOf(false) }
            var isErrorLocation by remember { mutableStateOf(false) }
            var isErrorCategory by remember { mutableStateOf(false) }
            var isErrorDescription by remember { mutableStateOf(false) }
            var isErrorContacts by remember { mutableStateOf(false) }

            val pickMedia =
                rememberLauncherForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
                    // Callback is invoked after the user selects a media item or closes the
                    // photo picker.
                    if (uri != null) {
                        selectedImagesUri = uri
                    }
                }
            val imageModifier = Modifier
                .fillMaxWidth()
                .size(150.dp)
                .border(BorderStroke(1.dp, Color.Black))
                .background(Color.Transparent)
                .clickable {
                    pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
                }
            Image(
                modifier = imageModifier,
                painter = if (selectedImagesUri == "".toUri()) painterResource(R.drawable.baseline_image_search_24)
                else rememberAsyncImagePainter(selectedImagesUri),
                contentScale = ContentScale.Fit,
                contentDescription = stringResource(id = R.string.event_image),
            )
            Row {
                TextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = nameText,
                    onValueChange = {
                        nameText = it
                        isErrorName = false
                    },
                    label = { Text(stringResource(R.string.name)) },
                    trailingIcon = {
                        if (nameText.isNotEmpty()) {
                            IconButton(onClick = { nameText = "" }) {
                                Icon(
                                    imageVector = Icons.Outlined.Close, contentDescription = null
                                )
                            }
                        }
                    },
                    maxLines = 3,
                    supportingText = {
                        if (isErrorName) {
                            Text(stringResource(R.string.empty_name))
                        }
                    },
                    isError = isErrorName
                )
            }
            Row {
                TextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = locationText,
                    onValueChange = {
                        locationText = it
                        isErrorLocation = false
                    },
                    label = { Text(stringResource(R.string.location)) },
                    trailingIcon = {
                        if (locationText.isNotEmpty()) {
                            IconButton(onClick = { locationText = "" }) {
                                Icon(
                                    imageVector = Icons.Outlined.Close, contentDescription = null
                                )
                            }
                        }
                    },
                    maxLines = 3,
                    supportingText = {
                        if (isErrorLocation) {
                            Text(stringResource(R.string.empty_location))
                        }
                    },
                    isError = isErrorLocation
                )
            }
            CategoriesRow { listCategories ->
                listCategories.forEach { categoryText[it] = it }
            }
            Row {
                TextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = descriptionText,
                    onValueChange = {
                        descriptionText = it
                        isErrorDescription = false
                    },
                    label = { Text(stringResource(R.string.description)) },
                    trailingIcon = {
                        if (descriptionText.isNotEmpty()) {
                            IconButton(onClick = { descriptionText = "" }) {
                                Icon(
                                    imageVector = Icons.Outlined.Close,
                                    contentDescription = null
                                )
                            }
                        }
                    },
                    maxLines = 10,
                    supportingText = {
                        if (isErrorDescription) {
                            Text(stringResource(R.string.empty_description))
                        }
                    },
                    isError = isErrorDescription
                )
            }
            Row {
                TextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = contactsText,
                    onValueChange = {
                        contactsText = it
                        isErrorContacts = false
                    },
                    label = { Text(stringResource(R.string.contacts)) },
                    trailingIcon = {
                        if (contactsText.isNotEmpty()) {
                            IconButton(onClick = { contactsText = "" }) {
                                Icon(
                                    imageVector = Icons.Outlined.Close,
                                    contentDescription = null
                                )
                            }
                        }
                    },
                    maxLines = 3,
                    supportingText = {
                        if (isErrorContacts) {
                            Text(stringResource(R.string.empty_contacts))
                        }
                    },
                    isError = isErrorContacts
                )
            }
            fun checkDetails(): Boolean {
                var isAllFilled = true
                if (nameText == "") {
                    isErrorName = true
                    isAllFilled = false
                }
                if (locationText == "") {
                    isErrorLocation = true
                    isAllFilled = false
                }
                if (categoryText.isEmpty()) {
                    isErrorCategory = true
                    isAllFilled = false
                }
                if (descriptionText == "") {
                    isErrorDescription = true
                    isAllFilled = false
                }
                if (contactsText == "") {
                    isErrorContacts = true
                    isAllFilled = false
                }
                return isAllFilled
            }
            Button(modifier = Modifier.fillMaxWidth(), onClick = {
                if (checkDetails()) {
                    viewModel.createNewEvent(
                        EventEntity(
                            id = System.currentTimeMillis().toString(),
                            imageUri = selectedImagesUri.toString(),
                            name = nameText,
                            location = locationText,
                            category = categoryText,
                            description = descriptionText,
                            contacts = contactsText
                        ), object : EventCreateListener {
                            override fun onSuccess() {
                                Toast.makeText(
                                    viewModel.appContext,
                                    "Event successfully created",
                                    Toast.LENGTH_SHORT
                                ).show()
                                navController.navigate(NavScreen.ACCOUNT) {
                                    popUpTo(NavScreen.NEW_EVENT_SCREEN) {
                                        inclusive = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            }

                            override fun onFailure(message: String?) {
                                Toast.makeText(
                                    viewModel.appContext,
                                    message,
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                    )
                }
            }) {
                Text(stringResource(R.string.add_new_event))
            }
        }
    }
}

