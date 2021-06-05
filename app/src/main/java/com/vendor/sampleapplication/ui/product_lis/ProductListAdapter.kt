package com.vendor.sampleapplication.ui.product_lis

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.vendor.sampleapplication.data.model.DatalistModel
import com.vendor.sampleapplication.databinding.LayoutProductListItemBinding

class ProductListAdapter(
    var context: Context,
   var valueProduct: List<DatalistModel>?
) : RecyclerView.Adapter<ProductListAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: LayoutProductListItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            LayoutProductListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding);
    }

    override fun getItemCount() = valueProduct!!.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       // holder.binding.title.text = _myproperties.value?.get(position)!!.title

        holder.binding.title.text= valueProduct?.get(position)!!.title
        holder.binding.description.text= valueProduct?.get(position)!!.description

    }
}
