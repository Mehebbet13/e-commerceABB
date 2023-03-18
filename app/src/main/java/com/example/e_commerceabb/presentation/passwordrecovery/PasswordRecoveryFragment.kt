package com.example.e_commerceabb.presentation.passwordrecovery

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.e_commerceabb.R
import com.example.e_commerceabb.databinding.FragmentPasswordRecoveryBinding
import com.example.e_commerceabb.utils.Constants

class PasswordRecoveryFragment : Fragment(R.layout.fragment_password_recovery) {
    lateinit var binding: FragmentPasswordRecoveryBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPasswordRecoveryBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setInputs()
        handleContinueButton()
        setListeners()
        binding.apply {
            recoveryInput.addTextChangedListener {
                setInputs()
                handleContinueButton()
            }
        }
    }

    private fun handleContinueButton() {
        val recoveryInputIsNotEmpty: Boolean = binding.recoveryInput.text?.isNotEmpty() == true
        if (recoveryInputIsNotEmpty) {
            binding.btnContinue.isClickable = true
            binding.btnContinue.alpha = 1f
        } else {
            binding.btnContinue.isClickable = false
            binding.btnContinue.alpha = 0.3f
        }
    }

    private fun setInputs() {
        if (binding.recoveryInput.text?.isNotEmpty() == true) {
            binding.recoveryInput.backgroundTintList = ContextCompat.getColorStateList(
                requireContext(),
                R.color.black
            )
        } else {
            binding.recoveryInput.backgroundTintList = ContextCompat.getColorStateList(
                requireContext(),
                R.color.grey20
            )
        }
    }

    private fun setListeners() {
        binding.apply {
            backButton.setOnClickListener {
                findNavController().navigateUp()
            }
            btnContinue.setOnClickListener {
                val bundle = bundleOf(
                    Constants.PIN_FROM to Constants.FROM_RECOVERY,
                    Constants.PIN_TITLE to getString(R.string.enter_recovery_code),
                    Constants.PIN_DESCRIPTION to getString(R.string.code_has_been_sent_to)
                )
                findNavController().navigate(R.id.action_passwordRecoveryFragment_to_pinFragment, bundle)
            }
        }
    }
}
