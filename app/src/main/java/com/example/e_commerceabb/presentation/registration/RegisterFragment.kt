package com.example.e_commerceabb.presentation.registration

import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
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
import com.example.e_commerceabb.utils.Constants.FROM_RECOVERY
import com.example.e_commerceabb.utils.Constants.PIN_DESCRIPTION
import com.example.e_commerceabb.utils.Constants.PIN_FROM
import com.example.e_commerceabb.utils.Constants.PIN_TITLE

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
            findNavController().navigate(R.id.action_registerFragment_to_signUpFragment)
        }
    }

    private fun setSuggestText() {
        val text = getText(R.string.sign_up_suggest_text)
        val spannable = SpannableString(text)
        spannable.setSpan(
            ForegroundColorSpan(ContextCompat.getColor(requireActivity(), R.color.main)),
            text.length - 7,
            text.length,
            Spanned.SPAN_INCLUSIVE_EXCLUSIVE
        )
        binding.suggestText.text = spannable
    }
}
