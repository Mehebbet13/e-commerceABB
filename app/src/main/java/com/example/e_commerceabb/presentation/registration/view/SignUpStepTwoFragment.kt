package com.example.e_commerceabb.presentation.registration.view

import android.content.Context
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.e_commerceabb.R
import com.example.e_commerceabb.data.api.Resource
import com.example.e_commerceabb.databinding.FragmentSignUpStepTwoBinding
import com.example.e_commerceabb.models.LoginRequest
import com.example.e_commerceabb.models.NewCustomerRequest
import com.example.e_commerceabb.presentation.registration.viewmodel.SignUpViewModel
import com.example.e_commerceabb.utils.Constants
import com.example.e_commerceabb.utils.Constants.EMPTY
import com.example.e_commerceabb.utils.TokenManager
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SignUpStepTwoFragment : Fragment(R.layout.fragment_sign_up_step_two) {

    lateinit var binding: FragmentSignUpStepTwoBinding
    private val viewModel: SignUpViewModel by viewModels({ this })

    private val isSignedIn by lazy {
        arguments?.getBoolean(Constants.IS_SIGNED_IN) ?: false
    }

    private val firstName by lazy {
        arguments?.getString(Constants.FIRST_NAME) ?: EMPTY
    }

    private val lastName by lazy {
        arguments?.getString(Constants.LAST_NAME) ?: EMPTY
    }

    private val username by lazy {
        arguments?.getString(Constants.USERNAME) ?: EMPTY
    }

    @Inject
    lateinit var tokenManager: TokenManager

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
        initView(isSignedIn)
        setListeners()
        setInputs()
        handleSignUpButton()
        observeCustomer()
        observeLogin()
        handleRememberMe()
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

    private fun observeCustomer() {
        viewModel.customer.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Success -> {
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
                is Resource.Error -> {
                    Toast.makeText(
                        requireContext(),
                        it.message,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    private fun handleRememberMe() {
        val sharedPref =
            context?.getSharedPreferences(Constants.MY_PREFS, Context.MODE_PRIVATE)
        val isUserRegistered = sharedPref?.getBoolean(Constants.IS_USER_REGISTERED, false)
        if (isUserRegistered != null) {
            binding.rememberMeCb.isChecked = isUserRegistered
        }
        binding.rememberMeCb.setOnCheckedChangeListener { _, _ ->
            if (binding.rememberMeCb.isChecked) {
                val sharedPreferences =
                    context?.getSharedPreferences(Constants.MY_PREFS, Context.MODE_PRIVATE)
                sharedPreferences?.edit()?.putBoolean(Constants.IS_USER_REGISTERED, true)?.apply()
            } else {
                val sharedPreferences =
                    context?.getSharedPreferences(Constants.MY_PREFS, Context.MODE_PRIVATE)
                sharedPreferences?.edit()?.putBoolean(Constants.IS_USER_REGISTERED, false)?.apply()
            }
        }
    }

    private fun observeLogin() {
        viewModel.login.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Success -> {
                    it.data?.token?.let { it1 -> tokenManager.saveToken(it1) }
                    findNavController().navigate(R.id.action_signUpStepTwoFragment_to_fillProfileFragment)
                }
                is Resource.Error -> {
                    Toast.makeText(
                        requireContext(),
                        it.message,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    private fun setSuggestText(text: String, isSigned: Boolean) {
        val spannable = SpannableString(text)
        val clickableSpan: ClickableSpan = object : ClickableSpan() {
            override fun onClick(textView: View) {
                if (isSigned) {
                    findNavController().navigate(R.id.action_signUpStepTwoFragment_to_signUpFragment)
                } else {
                    initView(true)
                    setListeners()
                }
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

    private fun initView(isSigned: Boolean) {
        if (isSigned) {
            binding.apply {
                stepTitle.text = getString(R.string.login_screen_title)
                btnContinue.text = getString(R.string.sign_in)
                val stepTitle = getString(R.string.sign_up_suggest_text)
                forgotPass.visibility = View.VISIBLE
                setSuggestText(stepTitle, true)
                forgotPass.setOnClickListener {
                    findNavController().navigate(R.id.action_signUpStepTwoFragment_to_passwordRecoveryFragment)
                }
            }
        } else {
            binding.apply {
                stepTitle.text = getString(R.string.sign_up_screen_title)
                btnContinue.text = getString(R.string.sign_up)
                val stepTitle = getString(R.string.sign_in_suggest_text)
                forgotPass.visibility = View.GONE
                setSuggestText(stepTitle, false)
            }
        }
    }

    private fun setListeners() {
        binding.apply {
            backButton.setOnClickListener {
                findNavController().navigateUp()
            }
            btnContinue.setOnClickListener {
                if (isSignedIn) {
                    val loginRequest = LoginRequest(
                        loginOrEmail = binding.emailInput.text.toString(),
                        password = binding.passwordInput.text.toString()
                    )
                    viewModel.logIn(loginRequest)
                } else {
                    val customerRequest = NewCustomerRequest(
                        firstName = firstName,
                        lastName = lastName,
                        login = username,
                        email = binding.emailInput.text.toString(),
                        password = binding.passwordInput.text.toString()
                    )
                    viewModel.createCustomer(customerRequest)
                }
            }
        }
    }
}
