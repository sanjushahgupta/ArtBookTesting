package com.digitalaya.artbooktesting.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.digitalaya.artbooktesting.R
import com.digitalaya.artbooktesting.adapter.ArtRecyclerAdapter
import com.digitalaya.artbooktesting.databinding.FragmentartBinding
import com.digitalaya.viewmodel.ArtViewModel
import javax.inject.Inject

class ArtFragment @Inject constructor(
    val artRecyclerAdapter: ArtRecyclerAdapter
): Fragment(R.layout.fragmentart) {

 private var fragmentBinding : FragmentartBinding? = null
    lateinit var viewModel : ArtViewModel

    private val swipeCallBack = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT){
        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean {
            return true
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            val layoutPosition = viewHolder.layoutPosition
            val selectedArt = artRecyclerAdapter.arts[layoutPosition]
            viewModel.deleteArt(selectedArt)
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[ArtViewModel::class.java]

        val binding = FragmentartBinding.bind(view)
        fragmentBinding = binding
        subscribeToObservers()
        binding.RecyclerViewArt.adapter = artRecyclerAdapter
        binding.RecyclerViewArt.layoutManager = LinearLayoutManager(requireContext())
        ItemTouchHelper(swipeCallBack).attachToRecyclerView(binding.RecyclerViewArt)

        binding.fab.setOnClickListener {
            findNavController().navigate(ArtFragmentDirections.actionArtFragmentToArtDetailsFragment())
        }


    }

    private fun subscribeToObservers(){
        viewModel.artList.observe(viewLifecycleOwner, Observer{

            artRecyclerAdapter.arts = it

        })
    }
    override fun onDestroyView() {
        fragmentBinding = null
        super.onDestroyView()

    }
}