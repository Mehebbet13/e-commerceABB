package com.example.e_commerceabb.data.repository

import com.example.e_commerceabb.models.NewCustomerRequest
import com.example.e_commerceabb.models.NewCustomerResponse

interface RegisterRepository {
    suspend fun createCustomer(request: NewCustomerRequest): NewCustomerResponse
}
