package com.digitalaya.artbooktesting.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.RequestManager
import com.bumptech.glide.module.AppGlideModule
import com.digitalaya.artbooktesting.R
import com.digitalaya.artbooktesting.databinding.ArtdetailsBinding
import javax.inject.Inject

class ArtDetailsFragment @Inject constructor(val glide : RequestManager): Fragment(R.layout.artdetails) {

    private var fragmentBinding: ArtdetailsBinding? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = ArtdetailsBinding.bind(view)
        fragmentBinding = binding
        binding.imageView.setOnClickListener {
            findNavController().navigate(ArtDetailsFragmentDirections.actionArtDetailsFragmentToImageApiFragment())
        }

    }

    override fun onDestroy() {
        fragmentBinding = null
        super.onDestroy()
    }

}