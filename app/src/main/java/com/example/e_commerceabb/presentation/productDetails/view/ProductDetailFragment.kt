package com.example.e_commerceabb.presentation.productDetails.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.example.e_commerceabb.R
import com.example.e_commerceabb.data.api.Resource
import com.example.e_commerceabb.databinding.FragmentProductDetailBinding
import com.example.e_commerceabb.models.ProductDetailPagerData
import com.example.e_commerceabb.presentation.productDetails.viewmodel.ProductDetailsViewModel
import com.example.e_commerceabb.utils.Constants.EMPTY
import com.example.e_commerceabb.utils.Constants.INDEX
import com.example.e_commerceabb.utils.Constants.ITEM_NO
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductDetailFragment : Fragment() {
    private lateinit var binding: FragmentProductDetailBinding
    private var currentItem = INDEX
    private var viewPager2: ViewPager2? = null
    private val productDetailPagerDataList = arrayListOf<ProductDetailPagerData>()
    private val viewModel: ProductDetailsViewModel by viewModels({ this })
    private val filteredAdapter by lazy { ProductDetailAdapter() }
    private val itemNO by lazy { arguments?.getString(ITEM_NO, null) }
    var id: String? = null
    var name: String? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProductDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setTitle()
        setViewPager()
        viewModel.getProductDetails(itemNO ?: EMPTY)
        binding.toolbar.setNavigationOnClickListener {
            activity?.onBackPressed()
        }
        setAdapter()
        viewModel.productDetail.observe(viewLifecycleOwner) {
            binding.productDetailTitle.text = it.name
            binding.productDetailSubtitle.text = it.description
            binding.productDetailCurrentPrice.text = "${it.currentPrice} ${"$"}"
            binding.productDetailPreviousPrice.text = "${it.previousPrice} ${"$"}"
            id = it.id
            viewModel.getFilteredProduct(it.name)
        }
        viewModel.filteredProduct.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Success -> {
                    it.data?.products?.let { item ->
                        filteredAdapter.setData(item)
                        if (item.isNullOrEmpty()) {
                            binding.similarGoods.visibility = View.GONE
                            binding.seeAll.visibility = View.GONE
                        }
                    }

                }
            }
        }
        binding.btnAddToCart.setOnClickListener {
            viewModel.addToCard(id ?: EMPTY)
        }
    }

    private fun setTitle() {
        binding.specifications.productTxt.setText(R.string.specifications)
        binding.reviewLayout.productTxt.setText(R.string.reviews)
        binding.questionsLayout.productTxt.setText(R.string.questions)
        binding.specifications.count.visibility = View.GONE
    }

    private fun setAdapterData() {
        val step1 = ProductDetailPagerData(
            R.drawable.rectangle_14,
        )
        val step2 = ProductDetailPagerData(
            R.drawable.onboarding_step3_image,
        )
        val step3 = ProductDetailPagerData(
            R.drawable.onboarding_step3_image,
        )
        val dataList = arrayListOf(step1, step2, step3)
        productDetailPagerDataList.clear()
        productDetailPagerDataList.addAll(dataList)
    }

    private fun setAdapter() {
        binding.productDetailRv.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.productDetailRv.adapter = filteredAdapter
    }

    private fun setViewPager() {
        setAdapterData()
        val adapter = ProductDetailViewPagerAdapter(productDetailPagerDataList)
        viewPager2 = binding.viewPagerProduct
        viewPager2?.adapter = adapter
        viewPager2?.setCurrentItem(currentItem, true)
        viewPager2?.let { vPager ->
            TabLayoutMediator(
                binding.tabLayoutProduct,
                vPager
            ) { _, _ ->
                viewPager2?.setCurrentItem(
                    currentItem,
                    true
                )
            }.attach()
            viewPager2?.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    currentItem = position
                }
            })
        }
    }
}