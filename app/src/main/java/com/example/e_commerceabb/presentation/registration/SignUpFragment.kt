package com.example.e_commerceabb.presentation.registration

import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.e_commerceabb.R
import com.example.e_commerceabb.databinding.FragmentSignUpBinding

class SignUpFragment : Fragment(R.layout.fragment_sign_up) {

    lateinit var binding: FragmentSignUpBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSignUpBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setSuggestText()
        setListeners()
        setInputs()
        handleSignUpButton()
        binding.apply {
            firstName.addTextChangedListener {
                setInputs()
                handleSignUpButton()
            }
            lastName.addTextChangedListener {
                setInputs()
                handleSignUpButton()
            }
            username.addTextChangedListener {
                setInputs()
                handleSignUpButton()
            }
        }
    }

    private fun setInputs() {
        binding.apply {
            if (firstName.text?.isNotEmpty() == true) {
                firstName.backgroundTintList = ContextCompat.getColorStateList(
                    requireContext(),
                    R.color.black
                )
            } else {
                firstName.backgroundTintList = ContextCompat.getColorStateList(
                    requireContext(),
                    R.color.grey20
                )
            }
            if (lastName.text?.isNotEmpty() == true) {
                lastName.backgroundTintList = ContextCompat.getColorStateList(
                    requireContext(),
                    R.color.black
                )
            } else {
                lastName.backgroundTintList = ContextCompat.getColorStateList(
                    requireContext(),
                    R.color.grey20
                )
            }
            if (username.text?.isNotEmpty() == true) {
                username.backgroundTintList = ContextCompat.getColorStateList(
                    requireContext(),
                    R.color.black
                )
            } else {
                username.backgroundTintList = ContextCompat.getColorStateList(
                    requireContext(),
                    R.color.grey20
                )
            }
        }
    }

    private fun setSuggestText() {
        val text = getText(R.string.sign_in_suggest_text)
        val spannable = SpannableString(text)
        spannable.setSpan(
            ForegroundColorSpan(ContextCompat.getColor(requireActivity(), R.color.main)),
            text.length - 7,
            text.length,
            Spanned.SPAN_INCLUSIVE_EXCLUSIVE
        )
        binding.suggestText.text = spannable
    }

    private fun handleSignUpButton() {
        val usernameIsNotEmpty = binding.username.text?.isNotEmpty() == true
        val lastNameIsNotEmpty = binding.lastName.text?.isNotEmpty() == true
        val firstNameIsNotEmpty = binding.firstName.text?.isNotEmpty() == true
        if (usernameIsNotEmpty && lastNameIsNotEmpty && firstNameIsNotEmpty) {
            binding.btnContinue.isClickable = true
            binding.btnContinue.alpha = 1f
        } else {
            binding.btnContinue.isClickable = false
            binding.btnContinue.alpha = 0.3f
        }
    }

    private fun setListeners() {
        binding.backButton.setOnClickListener {
            findNavController().navigateUp()
        }
        binding.btnContinue.setOnClickListener {
            findNavController().navigate(R.id.action_signUpFragment_to_signUpStepTwoFragment)
        }
    }
}
