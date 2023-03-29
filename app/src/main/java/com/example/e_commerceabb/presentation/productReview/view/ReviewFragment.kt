package com.example.e_commerceabb.presentation.productReview.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.e_commerceabb.R
import com.example.e_commerceabb.databinding.FragmentReviewBinding
import com.example.e_commerceabb.presentation.productReview.viewmodel.ReviewViewModel
import com.example.e_commerceabb.utils.Constants
import com.example.e_commerceabb.utils.Constants.EMPTY
import com.example.e_commerceabb.utils.load
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ReviewFragment : Fragment() {
    private lateinit var binding: FragmentReviewBinding
    private val reviewAdapter by lazy(LazyThreadSafetyMode.NONE) { ReviewAdapter() }
    private val viewModel: ReviewViewModel by viewModels({ this })
    private val productId by lazy { arguments?.getString(Constants.PRODUCT_ID, null) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentReviewBinding.inflate(inflater, container, false)
        return inflater.inflate(R.layout.fragment_review, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setAdapter()
        viewModel.getComments(productId ?: EMPTY)
        viewModel.comments.observe(viewLifecycleOwner) {
            if (it.isNullOrEmpty())
                with(binding) {
                    if (it.isNullOrEmpty().not()) {
                        emptyText.visibility = View.GONE
                        image.visibility = View.VISIBLE
                    }
                    it?.forEach { response ->
                        val product = response.product.product
                        productImage.load(product.imageUrls.getOrNull(0) ?: EMPTY)
                        productName.text = product.name
                        discountAmountProduct.text = product.currentPrice.toString()
                        amountProduct.text = product.previousPrice.toString()
                    }
                }
            reviewAdapter.setData(it)
        }
    }

    private fun setAdapter() {
        binding.rvReviews.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.rvReviews.adapter = reviewAdapter
    }
}