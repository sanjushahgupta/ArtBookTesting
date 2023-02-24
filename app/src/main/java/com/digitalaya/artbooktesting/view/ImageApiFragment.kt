package com.digitalaya.artbooktesting.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.digitalaya.artbooktesting.R
import com.digitalaya.artbooktesting.adapter.imageRecyclerAdapter
import com.digitalaya.artbooktesting.databinding.FragmentartBinding
import com.digitalaya.artbooktesting.databinding.FragmentsimageapiBinding
import javax.inject.Inject

class ImageApiFragment @Inject constructor(
    private val imageRecyclerAdapter: imageRecyclerAdapter
): Fragment(R.layout.fragmentsimageapi) {

    private var fragmentBinding : FragmentsimageapiBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentsimageapiBinding.bind(view)
        fragmentBinding = binding

        binding.imageRecyclerView
    }

    override fun onDestroy() {
        fragmentBinding = null
        super.onDestroy()
    }

}