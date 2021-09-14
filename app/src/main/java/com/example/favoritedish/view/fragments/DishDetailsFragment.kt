package com.example.favoritedish.view.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.favoritedish.R
import com.example.favoritedish.application.FavDishApplication
import com.example.favoritedish.databinding.FragmentDishDetailsBinding
import com.example.favoritedish.viewmodel.FavDishViewModel
import com.example.favoritedish.viewmodel.FavDishViewModelFactory
import java.io.IOException
import java.util.*


class DishDetailsFragment : Fragment() {

    private var mBinding: FragmentDishDetailsBinding? = null

    private val mFavDishViewModel: FavDishViewModel by viewModels {
        FavDishViewModelFactory((requireActivity().application as FavDishApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = FragmentDishDetailsBinding.inflate(inflater, container, false)
        return mBinding!!.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        //Get the required arguments that we have passed through action
        val args: DishDetailsFragmentArgs by navArgs()
        args.let {
            try {
                Glide.with(requireActivity())
                    .load(it.dishDetails.image)
                    .centerCrop()
                    .into(mBinding!!.ivDishImage)
            } catch (e: IOException){
                e.printStackTrace()
            }

            mBinding!!.tvTitle.text = it.dishDetails.title
            mBinding!!.tvType.text = it.dishDetails.type.capitalize(Locale.ROOT)
            mBinding!!.tvCategory.text = it.dishDetails.category
            mBinding!!.tvIngredients.text = it.dishDetails.ingredients
            mBinding!!.tvCookingDirection.text = it.dishDetails.directionToCook
            mBinding!!.tvCookingTime.text =
                resources.getString(R.string.lbl_estimate_cooking_time, it.dishDetails.cookingTime)

            if (args.dishDetails.favoriteDish){
                mBinding!!.ivFavoriteDish.setImageDrawable(
                    ContextCompat.getDrawable(
                        requireActivity(),
                        R.drawable.ic_favorite_selected
                    )
                )
            } else{
                mBinding!!.ivFavoriteDish.setImageDrawable(
                    ContextCompat.getDrawable(
                        requireActivity(),
                        R.drawable.ic_favorite_unselected
                    )
                )
            }

            mBinding!!.ivFavoriteDish.setOnClickListener {
                args.dishDetails.favoriteDish = !args.dishDetails.favoriteDish  //true to false, false to true

                mFavDishViewModel.update(args.dishDetails)  //pass the updated values to ViewModel

                if (args.dishDetails.favoriteDish) {
                    mBinding!!.ivFavoriteDish.setImageDrawable(
                        ContextCompat.getDrawable(
                            requireActivity(),
                            R.drawable.ic_favorite_selected
                        )
                    )

                    Toast.makeText(
                        requireActivity(),
                        resources.getString(R.string.msg_added_to_favorites),
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    mBinding!!.ivFavoriteDish.setImageDrawable(
                        ContextCompat.getDrawable(
                            requireActivity(),
                            R.drawable.ic_favorite_unselected
                        )
                    )

                    Toast.makeText(
                        requireActivity(),
                        resources.getString(R.string.msg_removed_from_favorite),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mBinding = null //mBinding null that is avoid the memory leaks
    }
}