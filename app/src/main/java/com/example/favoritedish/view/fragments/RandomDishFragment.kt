package com.example.favoritedish.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.favoritedish.databinding.FragmentRandomDishBinding

import com.example.favoritedish.viewmodel.SlideshowViewModel

class RandomDishFragment : Fragment() {

   private lateinit var mBinding: FragmentRandomDishBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        mBinding = FragmentRandomDishBinding.inflate(inflater, container, false)

        return mBinding.root
    }


}