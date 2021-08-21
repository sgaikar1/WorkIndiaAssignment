package com.sgaikar.workindia.ui.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sgaikar.workindia.R
import com.sgaikar.workindia.dbutilities.FoodItem
import com.sgaikar.workindia.ui.adapters.FoodListAdapter.RecyclerViewHolders

class FoodListAdapter(private var foodList: List<FoodItem>) :
    RecyclerView.Adapter<RecyclerViewHolders>() {
    fun setData(data: List<FoodItem>) {
        foodList = data
    }

    inner class RecyclerViewHolders internal constructor(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        val tName: TextView
        val tPrice: TextView
        val tvExtra: TextView
        val llParent: RelativeLayout

        init {
            llParent = itemView.findViewById(R.id.llSquare)
            tName = itemView.findViewById(R.id.tvName)
            tPrice = itemView.findViewById(R.id.tvPrice)
            tvExtra = itemView.findViewById(R.id.tvExtra)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolders {
        @SuppressLint("InflateParams") val layoutView =
            LayoutInflater.from(parent.context).inflate(R.layout.layout_food_item, null)
        return RecyclerViewHolders(layoutView)
    }

    override fun onBindViewHolder(holder: RecyclerViewHolders, position: Int) {
        val foodDetails = foodList[holder.adapterPosition]
        holder.tName.text = foodDetails.name
        holder.tPrice.text = foodDetails.price
        holder.tvExtra.text = foodDetails.extra
    }

    override fun getItemCount(): Int {
        return foodList.size
    }
}