package com.example.favoritedish.view.adapters

import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.favoritedish.databinding.ItemCustomListLayoutBinding
import com.example.favoritedish.view.activities.AddUpdateDishesActivity
import com.example.favoritedish.view.fragments.AllDishesFragment

class CustomListItemAdapter(
    private val activity: Activity,
    private val fragment: Fragment?,
    private val listItem: List<String>,
    private val selection: String
) : RecyclerView.Adapter<CustomListItemAdapter.ViewHolder>()
{
    /**
     * Inflates the item views which is designed in xml layout file
     *
     * create a new
     * {@link ViewHolder} and initializes some private fields to be used by RecyclerView.
     */
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val binding: ItemCustomListLayoutBinding =
            ItemCustomListLayoutBinding.inflate(LayoutInflater.from(activity), parent, false)

        return ViewHolder(binding)
    }

    /**
     * Binds each item in the ArrayList to a view
     *
     * Called when RecyclerView needs a new {@link ViewHolder} of the given type to represent
     * an item.
     *
     * This new ViewHolder should be constructed with a new View that can represent the items
     * of the given type. You can either create a new View manually or inflate it from an XML
     * layout file.
     */
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = listItem[position]
        holder.tvText.text = item

        //define the ItemView click event and send the result to the base class
        holder.itemView.setOnClickListener{
            if(activity is AddUpdateDishesActivity){
                activity.selectedListItem(item, selection)
            }

            if (fragment is AllDishesFragment){
                fragment.filterSelection(item)
            }
        }
    }

    /**
     * Gets the number of items in the list
     */
    override fun getItemCount(): Int {
        return listItem.size
    }

    /**
     * A ViewHolder describes an item view and metadata about its place within the RecyclerView.
     */
    class ViewHolder(view: ItemCustomListLayoutBinding): RecyclerView.ViewHolder(view.root) {
        val tvText = view.tvText    //Holds the TextView that will add each item to
    }
}