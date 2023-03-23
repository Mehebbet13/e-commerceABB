package com.example.e_commerceabb.presentation.registration.view

import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.e_commerceabb.R
import com.example.e_commerceabb.databinding.FragmentRegisterBinding
import com.example.e_commerceabb.utils.Constants.IS_SIGNED_IN

class RegisterFragment : Fragment(R.layout.fragment_register) {
    private lateinit var binding: FragmentRegisterBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentRegisterBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setSuggestText()
        setListeners()
    }

    private fun setListeners() {
        binding.backButton.setOnClickListener {
            findNavController().navigateUp()
        }
        binding.register.setOnClickListener {
            val bundle = bundleOf(
                IS_SIGNED_IN to true
            )
            findNavController().navigate(R.id.action_registerFragment_to_signUpStepTwoFragment, bundle)
        }
        binding.register.setOnClickListener {
            findNavController().navigate(R.id.action_registerFragment_to_homeFragment)
        }
    }

    private fun setSuggestText() {
        val text = getText(R.string.sign_up_suggest_text)
        val spannable = SpannableString(text)
        val clickableSpan: ClickableSpan = object : ClickableSpan() {
            override fun onClick(textView: View) {
                findNavController().navigate(R.id.action_registerFragment_to_signUpFragment)
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
}
