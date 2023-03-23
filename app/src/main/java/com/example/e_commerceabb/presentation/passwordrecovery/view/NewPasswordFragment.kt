package com.example.e_commerceabb.presentation.passwordrecovery.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.e_commerceabb.R
import com.example.e_commerceabb.data.api.Resource
import com.example.e_commerceabb.databinding.FragmentNewPasswordBinding
import com.example.e_commerceabb.dialogs.CustomDialogFragment
import com.example.e_commerceabb.models.UpdatePasswordRequest
import com.example.e_commerceabb.presentation.passwordrecovery.viewmodel.NewPasswordViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewPasswordFragment : Fragment(R.layout.fragment_new_password) {
    lateinit var binding: FragmentNewPasswordBinding
    private val viewModel: NewPasswordViewModel by viewModels({ this })

    private var dialog: CustomDialogFragment? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNewPasswordBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListeners()
        setInputs()
        handleSignUpButton()
        observeNewPassword()
        binding.apply {
            passwordInput.addTextChangedListener {
                setInputs()
                handleSignUpButton()
            }
            passwordInputNew.addTextChangedListener {
                setInputs()
                handleSignUpButton()
            }
        }
    }

    private fun observeNewPassword() {
        viewModel.newPassword.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Success -> {
                    showSuccessDialog()
                }
                is Resource.Error -> {
                    Log.e("mike err np", it.data.toString())
                }
            }
        }
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
        if (binding.passwordInputNew.text?.isNotEmpty() == true) {
            binding.passwordNew.setStartIconTintList(
                ContextCompat.getColorStateList(
                    requireContext(),
                    R.color.black
                )
            )
            binding.passwordNew.setEndIconTintList(
                ContextCompat.getColorStateList(
                    requireContext(),
                    R.color.main
                )
            )
            binding.passwordInputNew.backgroundTintList = ContextCompat.getColorStateList(
                requireContext(),
                R.color.black
            )
        } else {
            binding.passwordNew.setStartIconTintList(
                ContextCompat.getColorStateList(
                    requireContext(),
                    R.color.grey40
                )
            )
            binding.passwordNew.setEndIconTintList(
                ContextCompat.getColorStateList(
                    requireContext(),
                    R.color.grey40
                )
            )
            binding.passwordInputNew.backgroundTintList = ContextCompat.getColorStateList(
                requireContext(),
                R.color.grey20
            )
        }
    }

    private fun handleSignUpButton() {
        val emailIsNotEmpty = binding.passwordInputNew.text?.isNotEmpty() == true
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
                val request = UpdatePasswordRequest(
                    password = binding.passwordInput.text.toString(),
                    newPassword = binding.passwordInputNew.text.toString()
                )
                viewModel.updatePassword(request)
            }
        }
    }

    private fun navigateToHome() {
        Log.e("mike", "salam hehe")
    }

    private fun showSuccessDialog() {
        val dialogBuilder = CustomDialogFragment.Builder()
            .image(R.drawable.congratulation_image)
            .labelText(getString(R.string.new_pass_success))
            .descriptionText(getString(R.string.new_pass_success_description))
            .callback { navigateToHome() }

        dialog = dialogBuilder.build()

        dialog?.show(parentFragmentManager, CustomDialogFragment::class.java.canonicalName)
    }
}
