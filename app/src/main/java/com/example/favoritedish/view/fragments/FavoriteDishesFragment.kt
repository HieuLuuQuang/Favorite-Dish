package com.example.favoritedish.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.favoritedish.databinding.FragmentFavoriteDishesBinding

import com.example.favoritedish.viewmodel.GalleryViewModel

class FavoriteDishesFragment : Fragment() {

    private lateinit var mBinding: FragmentFavoriteDishesBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = FragmentFavoriteDishesBinding.inflate(inflater, container, false)
        return mBinding.root
    }


}