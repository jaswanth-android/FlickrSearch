package com.example.cvscodingchallenge.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.cvscodingchallenge.data.model.FlickrImage
import com.example.cvscodingchallenge.ui.viewmodel.FlickrViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FlickrSearchScreen(viewModel: FlickrViewModel) {
    var searchQuery by remember { mutableStateOf("") }
    val images by viewModel.images
    val isLoading by viewModel.isLoading

    // State to track the selected image for detail view
    var selectedImage by remember { mutableStateOf<FlickrImage?>(null) }

    // If an image is selected, show the detail screen
    selectedImage?.let { image ->
        FlickrImageDetailScreen(
            image = image,
            onBackClick = { selectedImage = null }
        )
    } ?: run {
        Column {
            TextField(
                value = searchQuery,
                onValueChange = {
                    searchQuery = it
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                placeholder = { Text("Search Flickr Images") },
                leadingIcon = {
                    Icon(Icons.Default.Search, contentDescription = "Search")
                }
            )

            LaunchedEffect(searchQuery) {
                if (searchQuery.isNotBlank()) {
                    viewModel.searchImages(searchQuery)
                }
            }

            if (isLoading) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.TopCenter)
                    )
                }
            } else {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(3),
                    contentPadding = PaddingValues(4.dp)
                ) {
                    items(images) { image ->
                        ImageThumbnail(
                            image = image,
                            onClick = { selectedImage = image }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ImageThumbnail(
    image: FlickrImage,
    onClick: () -> Unit
) {
    AsyncImage(
        model = image.media.imageUrl,
        contentDescription = image.title,
        modifier = Modifier
            .size(120.dp)
            .padding(4.dp)
            .clickable(onClick = onClick),
        contentScale = ContentScale.Crop
    )
}