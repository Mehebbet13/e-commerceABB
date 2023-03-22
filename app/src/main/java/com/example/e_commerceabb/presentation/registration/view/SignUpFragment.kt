package com.example.e_commerceabb.presentation.registration.view

import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.e_commerceabb.R
import com.example.e_commerceabb.databinding.FragmentSignUpBinding
import com.example.e_commerceabb.utils.Constants

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
        val clickableSpan: ClickableSpan = object : ClickableSpan() {
            override fun onClick(textView: View) {
                val bundle = bundleOf(
                    Constants.IS_SIGNED_IN to true
                )
                findNavController().navigate(
                    R.id.action_signUpFragment_to_signUpStepTwoFragment,
                    bundle
                )
            }

            override fun updateDrawState(drawState: TextPaint) {
                super.updateDrawState(drawState)
                drawState.isUnderlineText = false
                drawState.color = ContextCompat.getColor(requireActivity(), R.color.main)
            }
        }
        spannable.setSpan(
            clickableSpan,
            text.length - 7,
            text.length,
            Spanned.SPAN_INCLUSIVE_EXCLUSIVE
        )
        binding.suggestText.movementMethod = LinkMovementMethod.getInstance()
        binding.suggestText.text = spannable
        binding.suggestText.highlightColor =
            ContextCompat.getColor(requireContext(), R.color.transparent)
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
            val bundle = bundleOf(
                Constants.FIRST_NAME to binding.firstName.text.toString(),
                Constants.LAST_NAME to binding.lastName.text.toString(),
                Constants.USERNAME to binding.username.text.toString()
            )
            findNavController().navigate(
                R.id.action_signUpFragment_to_signUpStepTwoFragment,
                bundle
            )
        }
    }
}
