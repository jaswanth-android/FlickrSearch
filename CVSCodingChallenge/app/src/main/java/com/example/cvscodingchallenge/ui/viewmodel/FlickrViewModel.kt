package com.example.cvscodingchallenge.ui.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cvscodingchallenge.data.model.FlickrImage
import com.example.cvscodingchallenge.data.repository.FlickrRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FlickrViewModel @Inject constructor(
    private val repository: FlickrRepository
) : ViewModel() {

    private val _images = mutableStateOf<List<FlickrImage>>(emptyList())
    val images: State<List<FlickrImage>> = _images

    private val _isLoading = mutableStateOf(false)
    val isLoading: State<Boolean> = _isLoading

    fun searchImages(query: String) {
        viewModelScope.launch {
            _isLoading.value = true
            val result = repository.searchImages(query)
            _images.value = result
            _isLoading.value = false
        }
    }

}