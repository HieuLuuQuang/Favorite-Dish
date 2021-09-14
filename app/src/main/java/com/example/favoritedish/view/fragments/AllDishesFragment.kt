package com.example.favoritedish.view.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.favoritedish.R
import com.example.favoritedish.application.FavDishApplication
import com.example.favoritedish.databinding.FragmentAllDishesBinding
import com.example.favoritedish.model.entities.FavDish
import com.example.favoritedish.view.activities.AddUpdateDishesActivity
import com.example.favoritedish.view.adapters.FavDishAdapter
import com.example.favoritedish.viewmodel.FavDishViewModel
import com.example.favoritedish.viewmodel.FavDishViewModelFactory
import com.example.favoritedish.viewmodel.HomeViewModel

class AllDishesFragment : Fragment() {

    private lateinit var mBinding: FragmentAllDishesBinding

    /**
     * To create the ViewModel we used the viewModels delegate, passing in an instance of our FavDishViewModelFactory.
     * This is constructed based on the repository retrieved from the FavDishApplication.
     */
    private val mFavDishViewModel: FavDishViewModel by viewModels {
        FavDishViewModelFactory((requireActivity().application as FavDishApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        mBinding = FragmentAllDishesBinding.inflate(inflater, container,false)

        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //set the LayoutManager that this RecycleView will use
        mBinding.rvDishesList.layoutManager = GridLayoutManager(requireActivity(), 1)

        //adapter class is initialized and list is passed in the param
        val favDishAdapter = FavDishAdapter(this@AllDishesFragment)
        mBinding.rvDishesList.adapter = favDishAdapter

        /**
         * Add an observer on the LiveData returned by getAllDishesList.
         * The onChanged() method fires when the observed data changes and the activity is in the foreground.
         */
        mFavDishViewModel.allDishesList.observe(viewLifecycleOwner){
            dishes ->
                dishes.let {
                    if (it.isNotEmpty()) {

                        mBinding.rvDishesList.visibility = View.VISIBLE
                        mBinding.tvNoDishesAddedYet.visibility = View.GONE

                        favDishAdapter.dishesList(it)
                    } else {

                        mBinding.rvDishesList.visibility = View.GONE
                        mBinding.tvNoDishesAddedYet.visibility = View.VISIBLE
                    }
                }
        }
    }

    fun dishDetails(favDish: FavDish){
        findNavController().navigate(AllDishesFragmentDirections.actionNavigationAllDishesToNavigationDishDetails(favDish))
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.action_add_dish ->{
                startActivity(Intent(requireActivity(), AddUpdateDishesActivity::class.java))
                return true
            }
        }

        return super.onOptionsItemSelected(item)
    }
}