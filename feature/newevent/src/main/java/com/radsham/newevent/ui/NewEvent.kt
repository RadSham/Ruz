package com.radsham.newevent.ui

import android.content.ContentResolver
import android.content.ContentValues
import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.os.Environment
import android.provider.MediaStore
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.radsham.core_api.model.EventEntity
import com.radsham.newevent.R
import com.radsham.newevent.viewmodel.NewEventViewModel
import java.io.ByteArrayOutputStream
import java.io.OutputStream


@Composable
fun NewEvent(navController: NavHostController) {

    val viewModel: NewEventViewModel = hiltViewModel()
    Column(
        modifier = Modifier.padding(20.dp)
    ) {

        val defaultUri = rememberResourceUri(R.drawable.baseline_image_search_24)
        var selectedImagesUri:Uri by remember { mutableStateOf(defaultUri) }
        var nameText by remember { mutableStateOf("Simple event") }
        var categoryText by remember { mutableStateOf("Funny category") }
        var descriptionText by remember { mutableStateOf("Laconic description") }
        var contactsText by remember { mutableStateOf("Real contacts") }
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
            painter = rememberAsyncImagePainter(selectedImagesUri),
            contentScale = ContentScale.Fit,
            contentDescription = stringResource(id = R.string.event_image),
        )
        Row {
            TextField(modifier = Modifier.fillMaxWidth(),
                value = nameText,
                onValueChange = { nameText = it },
                label = { Text("Name") },
                trailingIcon = {
                    if (nameText.isNotEmpty()) {
                        IconButton(onClick = { nameText = "" }) {
                            Icon(
                                imageVector = Icons.Outlined.Close, contentDescription = null
                            )
                        }
                    }
                })
        }
        Row {
            TextField(modifier = Modifier.fillMaxWidth(),
                value = categoryText,
                onValueChange = { categoryText = it },
                label = { Text("Category") },
                trailingIcon = {
                    if (categoryText.isNotEmpty()) {
                        IconButton(onClick = { categoryText = "" }) {
                            Icon(
                                imageVector = Icons.Outlined.Close, contentDescription = null
                            )
                        }
                    }
                })
        }
        Row {
            TextField(modifier = Modifier.fillMaxWidth(),
                value = descriptionText,
                onValueChange = { descriptionText = it },
                label = { Text("Description") },
                trailingIcon = {
                    if (descriptionText.isNotEmpty()) {
                        IconButton(onClick = { descriptionText = "" }) {
                            Icon(
                                imageVector = Icons.Outlined.Close, contentDescription = null
                            )
                        }
                    }
                })
        }
        Row {
            TextField(modifier = Modifier.fillMaxWidth(),
                value = contactsText,
                onValueChange = { contactsText = it },
                label = { Text("Contacts") },
                trailingIcon = {
                    if (contactsText.isNotEmpty()) {
                        IconButton(onClick = { contactsText = "" }) {
                            Icon(
                                imageVector = Icons.Outlined.Close, contentDescription = null
                            )
                        }
                    }
                })
        }
        Button(modifier = Modifier.fillMaxWidth(), onClick = {
            viewModel.sendNewEvent(
                EventEntity(
                    System.currentTimeMillis().toString(),
                    selectedImagesUri.toString(),
                    nameText,
                    categoryText,
                    descriptionText,
                    contactsText
                )
            )
        }) {
            Text("Add event")
        }
    }
}

@Composable
fun rememberResourceUri(resourceId: Int): Uri {
    val context = LocalContext.current
    return remember(resourceId) {
        with(context.resources) {
            Uri.Builder()
                .scheme(ContentResolver.SCHEME_ANDROID_RESOURCE)
                .authority(getResourcePackageName(resourceId))
                .appendPath(getResourceTypeName(resourceId))
                .appendPath(getResourceEntryName(resourceId))
                .build()
        }
    }
}

fun getImageUriFromBitmap(inContext: Context, inImage: Bitmap): Uri {
    val bytes = ByteArrayOutputStream()
    inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes)
    val path =
        MediaStore.Images.Media.insertImage(inContext.contentResolver, inImage, "Title", null)
    return Uri.parse(path)
}

//https://stackoverflow.com/a/66817176
fun saveImageInQ(context: Context, bitmap: Bitmap?): Uri? {
    val filename = "IMG_${System.currentTimeMillis()}.jpg"
    var fos: OutputStream?
    var imageUri: Uri?
    val contentValues = ContentValues().apply {
        put(MediaStore.MediaColumns.DISPLAY_NAME, filename)
        put(MediaStore.MediaColumns.MIME_TYPE, "image/jpg")
        put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_PICTURES)
        put(MediaStore.Images.Media.IS_PENDING, 1)
    }
    //use application context to get contentResolver
    val contentResolver = context.contentResolver
    contentResolver.also { resolver ->
        imageUri = resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)
        fos = imageUri?.let { resolver.openOutputStream(it) }
    }

    fos?.use { bitmap?.compress(Bitmap.CompressFormat.JPEG, 70, it) }

    contentValues.clear()
    contentValues.put(MediaStore.Video.Media.IS_PENDING, 0)
    imageUri?.let { contentResolver.update(it, contentValues, null, null) }
    return imageUri
}