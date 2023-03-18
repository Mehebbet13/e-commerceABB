package com.example.e_commerceabb.dialogs

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.example.e_commerceabb.R
import com.example.e_commerceabb.databinding.FragmentCustomDialogBinding
import com.example.e_commerceabb.utils.Constants.CALLBACK
import com.example.e_commerceabb.utils.Constants.DESCRIPTION_TEXT
import com.example.e_commerceabb.utils.Constants.IMAGE
import com.example.e_commerceabb.utils.Constants.LABEL_TEXT

class CustomDialogFragment : DialogFragment(R.layout.fragment_custom_dialog) {
    private var labelText: String? = null
    private var descriptionText: String? = null
    private var image: Int? = null
    lateinit var binding: FragmentCustomDialogBinding

    companion object {
        private var callback: (() -> Unit)? = null

        fun newInstance(
            image: Int?,
            labelText: String?,
            descriptionText: String?,
            callback: (() -> Unit)
        ): CustomDialogFragment {
            val bundle = bundleOf(
                IMAGE to image,
                LABEL_TEXT to labelText,
                DESCRIPTION_TEXT to descriptionText,
                CALLBACK to callback
            )
            this.callback = callback
            return CustomDialogFragment().apply {
                arguments = bundle
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCustomDialogBinding.inflate(inflater)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        image = arguments?.getInt(IMAGE)
        labelText = arguments?.getString(LABEL_TEXT)
        descriptionText = arguments?.getString(DESCRIPTION_TEXT)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }

    private fun initView() {
        image?.let {
            binding.dialogImage.setImageResource(it)
        }
        labelText?.let {
            binding.dialogTitle.text = it
        }
        descriptionText?.let {
            binding.dialogDescription.text = it
        }
    }

    data class Builder(
        private var image: Int? = null,
        private var labelText: String? = null,
        private var descriptionText: String? = null,
        private var callback: (() -> Unit)? = null
    ) {
        fun image(image: Int?) = apply { this.image = image }
        fun callback(callback: (() -> Unit)? = null) = apply { this.callback = callback }
        fun labelText(labelText: String?) = apply { this.labelText = labelText }
        fun descriptionText(descriptionText: String?) =
            apply { this.descriptionText = descriptionText }

        fun build() = callback?.let {
            newInstance(
                image,
                labelText,
                descriptionText,
                it
            )
        }
    }

    override fun show(manager: FragmentManager, tag: String?) {
        try {
            super.show(manager, tag)
            Handler(Looper.getMainLooper()).postDelayed({
                dialog?.dismiss()
                callback?.invoke()
            }, 5000)
        } catch (e: IllegalStateException) {
            Log.i("Exception%s", e.toString())
        }
    }
}
