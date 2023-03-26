package com.example.e_commerceabb.presentation.home.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.e_commerceabb.data.api.Resource
import com.example.e_commerceabb.data.repository.CustomerRepositoryImpl
import com.example.e_commerceabb.models.HomeCategoryListModel
import com.example.e_commerceabb.models.HomeProductsListModel
import com.example.e_commerceabb.presentation.home.mapper.HomeUiMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: CustomerRepositoryImpl,
    var mapper: HomeUiMapper
) : ViewModel() {

    private val _productsList = MutableLiveData<List<HomeProductsListModel>>()
    val productsList: LiveData<List<HomeProductsListModel>>
        get() = _productsList

    private val _catalog = MutableLiveData<List<HomeCategoryListModel>>()
    val catalog: LiveData<List<HomeCategoryListModel>>
        get() = _catalog

    fun getProducts() {
        viewModelScope.launch {
            when (val response = repository.getProducts()) {
                is Resource.Success -> {
                    response.data?.let {
                        _productsList.postValue(mapper.mapProductsToUiModel(it))
                    }
                }
            }
        }
    }

    fun getCategory() {
        viewModelScope.launch {
            when (val response = repository.getCatalog()) {
                is Resource.Success -> {
                    response.data?.let {
                        _catalog.postValue(mapper.mapCategoryToUiModel(it))
                    }
                }
            }
        }
    }
}
