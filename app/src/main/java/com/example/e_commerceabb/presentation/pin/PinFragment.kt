package com.example.e_commerceabb.presentation.pin

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
import com.example.e_commerceabb.databinding.FragmentPinBinding
import com.example.e_commerceabb.utils.Constants.FROM_RECOVERY
import com.example.e_commerceabb.utils.Constants.FROM_REGISTER
import com.example.e_commerceabb.utils.Constants.PIN_DESCRIPTION
import com.example.e_commerceabb.utils.Constants.PIN_FROM
import com.example.e_commerceabb.utils.Constants.PIN_TITLE

class PinFragment : Fragment(R.layout.fragment_pin) {

    lateinit var binding: FragmentPinBinding

    private val from by lazy {
        arguments?.getString(PIN_FROM)
    }
    private val pinTitle by lazy {
        arguments?.getString(PIN_TITLE)
    }
    private val pinDescription by lazy {
        arguments?.getString(PIN_DESCRIPTION)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPinBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        handlePinInputs()
        initView()
        handlePinVerifyButton()
        setResendText()
    }

    private fun handlePinInputs() {
        binding.apply {
            firstPin.addTextChangedListener {
                if (firstPin.text?.toList()?.size == 1) {
                    secondPin.requestFocus()
                } else if ((firstPin.text?.toList()?.size ?: 0) > 1) {
                    firstPin.text?.delete(1, 2)
                }
                handlePinVerifyButton()
            }
            secondPin.addTextChangedListener {
                if (secondPin.text?.toList()?.size == 1) {
                    thirdPin.requestFocus()
                } else if ((secondPin.text?.toList()?.size ?: 0) > 1) {
                    secondPin.text?.delete(1, 2)
                } else if ((secondPin.text?.toList()?.size ?: 0) == 0) {
                    firstPin.requestFocus()
                }
                handlePinVerifyButton()
            }
            thirdPin.addTextChangedListener {
                if (thirdPin.text?.toList()?.size == 1) {
                    forthPin.requestFocus()
                } else if ((thirdPin.text?.toList()?.size ?: 0) > 1) {
                    thirdPin.text?.delete(1, 2)
                } else if ((thirdPin.text?.toList()?.size ?: 0) == 0) {
                    secondPin.requestFocus()
                }
                handlePinVerifyButton()
            }
            forthPin.addTextChangedListener {
                if (forthPin.text?.toList()?.size == 1) {
                    forthPin.clearFocus()
                } else if ((forthPin.text?.toList()?.size ?: 0) > 1) {
                    forthPin.text?.delete(1, 2)
                } else if ((forthPin.text?.toList()?.size ?: 0) == 0) {
                    thirdPin.requestFocus()
                }
                handlePinVerifyButton()
            }
        }
    }

    private fun initView() {
        binding.apply {
            pinScreenTitle.text = pinTitle
            setDescriptionText()
        }
        when (from) {
            FROM_REGISTER -> {
                binding.continueWithContainer.visibility = View.VISIBLE
                binding.navTitle.visibility = View.GONE
                binding.verify.setOnClickListener {
                    findNavController().navigate(R.id.action_pinFragment_to_signUpStepTwoFragment)
                }
            }

            FROM_RECOVERY -> {
                binding.continueWithContainer.visibility = View.GONE
                binding.navTitle.visibility = View.VISIBLE
                binding.verify.setOnClickListener {
                    findNavController().navigate(R.id.action_pinFragment_to_newPasswordFragment)
                }
            }
        }
    }

    private fun handlePinVerifyButton() {
        val firstPinIsNotEmpty = binding.firstPin.text?.isNotEmpty() == true
        val secondPinIsNotEmpty = binding.secondPin.text?.isNotEmpty() == true
        val thirdPinIsNotEmpty = binding.thirdPin.text?.isNotEmpty() == true
        val forthPinIsNotEmpty = binding.forthPin.text?.isNotEmpty() == true
        val clickButton =
            firstPinIsNotEmpty && secondPinIsNotEmpty && thirdPinIsNotEmpty && forthPinIsNotEmpty
        if (clickButton) {
            binding.verify.isClickable = true
            binding.verify.alpha = 1f
        } else {
            binding.verify.isClickable = false
            binding.verify.alpha = 0.3f
        }
    }

    private fun setDescriptionText() {
        pinDescription?.let { text ->
            val spannable = SpannableString(text)
            spannable.setSpan(
                ForegroundColorSpan(ContextCompat.getColor(requireActivity(), R.color.main)),
                text.length - 17,
                text.length,
                Spanned.SPAN_INCLUSIVE_EXCLUSIVE
            )
            binding.pinScreenDescription.text = spannable
        }
    }

    private fun setResendText() {
        val text = getText(R.string.resend_code)
        val spannable = SpannableString(text)
        spannable.setSpan(
            ForegroundColorSpan(ContextCompat.getColor(requireActivity(), R.color.main)),
            text.length - 4,
            text.length - 2,
            Spanned.SPAN_INCLUSIVE_EXCLUSIVE
        )
        binding.pinScreenResend.text = spannable
    }
}
