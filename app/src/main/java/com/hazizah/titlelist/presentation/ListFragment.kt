package com.hazizah.titlelist.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hazizah.titlelist.R
import com.hazizah.titlelist.databinding.FragmentListBinding

class ListFragment : Fragment() {
    private val adapter by lazy { TitleAdapter() }
    private lateinit var binding: FragmentListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListBinding.inflate(inflater)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.titleList.adapter = adapter
    }
}