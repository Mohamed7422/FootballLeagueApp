package com.example.footballleagueapp.ui.utils

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import com.example.footballleagueapp.R

@Composable
fun LoadingImage(
    path: String,

    modifier: Modifier = Modifier
        .size(50.dp)
) {

    val imageUrl = remember {
        if(path == "null") {
            R.drawable.local_ic
        } else {
            path
        }
    }

    SubcomposeAsyncImage(
        model = imageUrl,
        contentDescription = "contentDescription",
        modifier = modifier,
        loading = { CircularProgressIndicator() },
        error = {
            Image(
                painter = painterResource(R.drawable.local_ic),
                contentDescription = null
            )
        }
    )
}