package com.example.rockpaperscissors

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.rockpaperscissors.databinding.MbsSelectObjectBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class ObjectSelectorModalBottomSheet : BottomSheetDialogFragment() {

    private var _binding: MbsSelectObjectBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = MbsSelectObjectBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val TAG = "ObjectSelectorModalBottomSheet"
    }
}