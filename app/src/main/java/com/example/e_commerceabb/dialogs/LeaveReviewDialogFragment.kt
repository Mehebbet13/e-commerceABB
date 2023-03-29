package com.example.e_commerceabb.dialogs

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.DialogFragment
import com.example.e_commerceabb.R
import com.example.e_commerceabb.databinding.FragmentLeaveReviewDialogBinding
import com.example.e_commerceabb.utils.Constants
import com.example.e_commerceabb.utils.load

class LeaveReviewDialogFragment : DialogFragment(R.layout.fragment_leave_review_dialog) {
    lateinit var binding: FragmentLeaveReviewDialogBinding
    private var title: String? = null
    private var descriptionText: String? = null
    private var image: String? = null
    var submitListener: (comment: String) -> Unit = {}

    companion object {
        fun newInstance(
            image: String?,
            labelText: String?,
            descriptionText: String?
        ): LeaveReviewDialogFragment {
            val bundle = bundleOf(
                Constants.IMAGE to image,
                Constants.LABEL_TEXT to labelText,
                Constants.DESCRIPTION_TEXT to descriptionText
            )
            return LeaveReviewDialogFragment().apply {
                arguments = bundle
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        image = arguments?.getString(Constants.IMAGE)
        title = arguments?.getString(Constants.LABEL_TEXT)
        descriptionText = arguments?.getString(Constants.DESCRIPTION_TEXT)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLeaveReviewDialogBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if(dialog != null) {
            dialog!!.window?.setGravity(Gravity.CENTER_HORIZONTAL)
            val p = dialog!!.window?.attributes
            p?.horizontalMargin = 100f
            p?.width = ViewGroup.LayoutParams.MATCH_PARENT
            dialog!!.window?.attributes = p
            dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }

        setStyle(STYLE_NO_FRAME, 0)
        setButton()
        binding.review.addTextChangedListener {
            setButton()
            if (binding.review.text?.isNotEmpty() == true) {
                binding.review.backgroundTintList = ContextCompat.getColorStateList(
                    requireContext(),
                    R.color.black
                )
            } else {
                binding.review.backgroundTintList = ContextCompat.getColorStateList(
                    requireContext(),
                    R.color.grey20
                )
            }
        }
        initView()
    }

    private fun initView() {
        image?.let {
            binding.imageProduct.load(it)
        }
        title?.let {
            binding.productName.text = it
        }
        descriptionText?.let {
            binding.productPrice.text = it
        }
    }

    private fun setButton() {
        if (binding.review.text?.isNotEmpty() == true) {
            binding.btnSubmit.isClickable = true
            binding.btnSubmit.alpha = 1f
        } else {
            binding.btnSubmit.isClickable = false
            binding.btnSubmit.alpha = 0.3f
        }
        binding.btnSubmit.setOnClickListener {
            submitListener.invoke(binding.review.text.toString())
        }
        binding.btnCancel.setOnClickListener {
            dialog?.dismiss()
        }
    }

    data class Builder(
        private var image: String? = null,
        private var labelText: String? = null,
        private var descriptionText: String? = null
    ) {
        fun image(image: String?) = apply { this.image = image }
        fun labelText(labelText: String?) = apply { this.labelText = labelText }
        fun descriptionText(descriptionText: String?) =
            apply { this.descriptionText = descriptionText }

        fun build() =
            newInstance(
                image,
                labelText,
                descriptionText
            )
    }
}

