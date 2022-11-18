package com.example.fitlywebcompose.routines

import androidx.compose.material.Icon
import androidx.compose.material.IconToggleButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.fitlywebcompose.data.getViewModelFactory
import com.example.fitlywebcompose.routines.RoutineScreenViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun FavoriteButton(
    viewModel: RoutineScreenViewModel ,
    id: Int,
    isFavorite: Boolean
) {

    IconToggleButton(
        checked = isFavorite,
        onCheckedChange = {
            if (isFavorite){
                viewModel.removeFavourite(id)
            }else{
                viewModel.addFavourite(id)
            }
        }
    ) {
        Icon(
            tint = MaterialTheme.colors.onPrimary,

            imageVector = if (isFavorite) {
                Icons.Filled.Favorite
            } else {
                Icons.Default.FavoriteBorder
            },
            contentDescription = null
        )
    }

}