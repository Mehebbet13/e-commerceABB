package com.example.e_commerceabb.presentation.productReview.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.e_commerceabb.data.api.Resource
import com.example.e_commerceabb.data.repository.ProductRepositoryImpl
import com.example.e_commerceabb.models.CommentsResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReviewViewModel @Inject constructor(
    private val repository: ProductRepositoryImpl,
) : ViewModel() {

    private val _comments = MutableLiveData<List<CommentsResponse>>()
    val comments: LiveData<List<CommentsResponse>>
        get() = _comments

    fun getComments(id: String) {
        try {
            viewModelScope.launch {
                when (val response = repository.getComment(id)) {
                    is Resource.Success-> {
                        response.data.let {
                            _comments.postValue(it)
                        }
                    }
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}