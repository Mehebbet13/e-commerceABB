package com.example.e_commerceabb.presentation.onboarding

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.e_commerceabb.R
import com.example.e_commerceabb.databinding.FragmentOnboardingBinding
import com.example.e_commerceabb.utils.Constants.IS_USER_REGISTERED
import com.example.e_commerceabb.utils.Constants.MY_PREFS

class OnboardingFragment : Fragment(R.layout.fragment_onboarding) {
    lateinit var binding: FragmentOnboardingBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOnboardingBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.logo.setOnClickListener {
            val scaleAnim = AnimationUtils.loadAnimation(requireContext(), R.anim.scale)
            binding.logo.startAnimation(scaleAnim)
            navigateToWelcomeScreen()
        }
    }

    private fun navigateToWelcomeScreen() {
        val sharedPreferences = context?.getSharedPreferences(MY_PREFS, Context.MODE_PRIVATE)
        val isUserRegistered = sharedPreferences?.getBoolean(IS_USER_REGISTERED, false)
        if (isUserRegistered == true) {
            findNavController().navigate(R.id.action_onboardingFragment_to_fillProfileFragment)
        } else {
            findNavController().navigate(R.id.action_onboardingFragment_to_welcomeFragment)
        }
    }
}
