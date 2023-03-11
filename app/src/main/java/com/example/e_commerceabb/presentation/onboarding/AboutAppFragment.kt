package com.example.e_commerceabb.presentation.onboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.e_commerceabb.R
import com.example.e_commerceabb.databinding.FragmentAboutAppBinding
import com.example.e_commerceabb.models.AboutAppData
import com.example.e_commerceabb.presentation.onboarding.adapter.AboutAppAdapter
import com.example.e_commerceabb.utils.Constants.INDEX
import com.example.e_commerceabb.utils.Constants.TWO
import com.google.android.material.tabs.TabLayoutMediator

class AboutAppFragment : Fragment(R.layout.fragment_about_app) {

    private lateinit var binding: FragmentAboutAppBinding

    private var currentItem = INDEX
    private var viewPager2: ViewPager2? = null
    private val dataAboutAppList = arrayListOf<AboutAppData>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAboutAppBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setViewPager()
        handleNextButton()
        setNextButtonText()
    }

    private fun setAdapterData() {
        val step1 = AboutAppData(
            R.drawable.onboarding_step1_image,
            getString(R.string.onboarding_step1)
        )
        val step2 = AboutAppData(
            R.drawable.onboarding_step2_image,
            getString(R.string.onboarding_step2)
        )
        val step3 = AboutAppData(
            R.drawable.onboarding_step3_image,
            getString(R.string.onboarding_step3)
        )
        val dataList = arrayListOf(step1, step2, step3)
        dataAboutAppList.clear()
        dataAboutAppList.addAll(dataList)
    }

    private fun setViewPager() {
        setAdapterData()
        val adapter = AboutAppAdapter(dataAboutAppList)
        viewPager2 = binding.viewPagerAppInfo
        viewPager2?.adapter = adapter
        viewPager2?.setCurrentItem(currentItem, false)
        viewPager2?.let { vPager ->
            TabLayoutMediator(
                binding.tabLayoutAppInfo,
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
                    setNextButtonText()
                }
            })
        }
    }

    private fun setNextButtonText() {
        if (currentItem == TWO) {
            binding.btnNext.text = getString(R.string.next_last_step)
        } else {
            binding.btnNext.text = getString(R.string.next)
        }
    }

    private fun handleNextButton() {
        binding.btnNext.setOnClickListener {
            setNextButtonText()
            if (currentItem == TWO) {
                findNavController().navigate(R.id.action_aboutAppFragment_to_registerFragment)
            } else {
                currentItem++
                setViewPager()
            }
        }
    }
}
