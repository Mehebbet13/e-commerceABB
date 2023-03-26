package com.example.e_commerceabb.dialogs

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.e_commerceabb.R
import com.example.e_commerceabb.databinding.FragmentCustomDialogBinding
import com.example.e_commerceabb.databinding.FragmentLeaveReviewDialogBinding

class LeaveReviewDialogFragment : DialogFragment(R.layout.fragment_leave_review_dialog) {
    lateinit var binding: FragmentLeaveReviewDialogBinding

    companion object {
        fun newInstance(): LeaveReviewDialogFragment {
            return LeaveReviewDialogFragment()
        }
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
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }
}
