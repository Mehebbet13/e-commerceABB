package com.example.e_commerceabb.presentation.registration

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
import com.example.e_commerceabb.databinding.FragmentSignUpStepTwoBinding
import com.example.e_commerceabb.utils.Constants

class SignUpStepTwoFragment : Fragment(R.layout.fragment_sign_up_step_two) {

    lateinit var binding: FragmentSignUpStepTwoBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSignUpStepTwoBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setSuggestText()
        setListeners()
        setInputs()
        handleSignUpButton()
        binding.apply {
            passwordInput.addTextChangedListener {
                setInputs()
                handleSignUpButton()
            }
            emailInput.addTextChangedListener {
                setInputs()
                handleSignUpButton()
            }
        }
    }

    private fun setSuggestText() {
        val text = getText(R.string.sign_in_suggest_text)
        val spannable = SpannableString(text)
        val clickableSpan: ClickableSpan = object : ClickableSpan() {
            override fun onClick(textView: View) {
                findNavController().navigateUp()
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

    private fun setInputs() {
        if (binding.passwordInput.text?.isNotEmpty() == true) {
            binding.password.setEndIconTintList(
                ContextCompat.getColorStateList(
                    requireContext(),
                    R.color.main
                )
            )
            binding.password.setStartIconTintList(
                ContextCompat.getColorStateList(
                    requireContext(),
                    R.color.black
                )
            )
            binding.passwordInput.backgroundTintList = ContextCompat.getColorStateList(
                requireContext(),
                R.color.black
            )
        } else {
            binding.password.setEndIconTintList(
                ContextCompat.getColorStateList(
                    requireContext(),
                    R.color.grey40
                )
            )
            binding.password.setStartIconTintList(
                ContextCompat.getColorStateList(
                    requireContext(),
                    R.color.grey40
                )
            )

            binding.passwordInput.backgroundTintList = ContextCompat.getColorStateList(
                requireContext(),
                R.color.grey20
            )
        }
        if (binding.emailInput.text?.isNotEmpty() == true) {
            binding.email.setStartIconTintList(
                ContextCompat.getColorStateList(
                    requireContext(),
                    R.color.black
                )
            )

            binding.emailInput.backgroundTintList = ContextCompat.getColorStateList(
                requireContext(),
                R.color.black
            )
        } else {
            binding.email.setStartIconTintList(
                ContextCompat.getColorStateList(
                    requireContext(),
                    R.color.grey40
                )
            )

            binding.emailInput.backgroundTintList = ContextCompat.getColorStateList(
                requireContext(),
                R.color.grey20
            )
        }
    }

    private fun handleSignUpButton() {
        val emailIsNotEmpty = binding.emailInput.text?.isNotEmpty() == true
        val passwordIsNotEmpty = binding.passwordInput.text?.isNotEmpty() == true
        if (emailIsNotEmpty && passwordIsNotEmpty) {
            binding.btnContinue.isClickable = true
            binding.btnContinue.alpha = 1f
        } else {
            binding.btnContinue.isClickable = false
            binding.btnContinue.alpha = 0.3f
        }
    }

    private fun setListeners() {
        binding.apply {
            backButton.setOnClickListener {
                findNavController().navigateUp()
            }
            btnContinue.setOnClickListener {
                val bundle = bundleOf(
                    Constants.PIN_FROM to Constants.FROM_REGISTER,
                    Constants.PIN_TITLE to getString(R.string.pin_screen_register_title),
                    Constants.PIN_DESCRIPTION to getString(R.string.register_code_has_been_sent_to)
                )
                findNavController().navigate(
                    R.id.action_signUpStepTwoFragment_to_pinFragment,
                    bundle
                )
            }
        }
    }
}
