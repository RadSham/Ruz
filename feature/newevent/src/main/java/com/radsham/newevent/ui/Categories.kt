package com.radsham.newevent.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import com.radsham.core_api.model.Categories
import com.radsham.core_api.model.names
import com.radsham.newevent.R

//https://www.geeksforgeeks.org/drop-down-menu-in-android-using-jetpack-compose/

@Composable
fun CategoriesRow(function: (List<String>) -> Unit) {
    var mExpanded by remember { mutableStateOf(false) }
    var mTextFieldSize by remember { mutableStateOf(Size.Zero) }
    val selectedCategoriesList = remember { mutableStateListOf<String>() }
    function(selectedCategoriesList.toList())
    val icon = if (mExpanded)
        Icons.Filled.KeyboardArrowDown
    else
        Icons.Filled.Add
    Row(
        modifier = Modifier
            .padding(bottom = 20.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        TextField(
            value = selectedCategoriesList.toList().joinToString(", "),
            onValueChange = { },
            readOnly = true,
            modifier = Modifier
                .weight(0.6f)
                .onGloballyPositioned { coordinates ->
                    // This value is used to assign to
                    // the DropDown the same width
                    mTextFieldSize = coordinates.size.toSize()
                },
            label = { Text(stringResource(id = R.string.category)) },
            trailingIcon = {
                Icon(icon, stringResource(id = R.string.category),
                    Modifier.clickable { mExpanded = !mExpanded })
            }
        )
        DropdownMenu(
            expanded = mExpanded,
            onDismissRequest = { mExpanded = false },
            modifier = Modifier
                .width(with(LocalDensity.current) { mTextFieldSize.width.toDp() })
                .padding(20.dp)
        ) {
            Categories.entries.names().forEach { label ->
                DropdownMenuItem(
                    text = { Text(text = label) },
                    onClick = {
                        if (!selectedCategoriesList.contains(label))
                            selectedCategoriesList.add(label)
                        mExpanded = false
                    })
            }
        }
        IconButton(colors = IconButtonDefaults.iconButtonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = Color.White
        ),
            onClick = {
                selectedCategoriesList.clear()
            }) {
            Icon(Icons.Default.Close, "")
        }
    }
}
