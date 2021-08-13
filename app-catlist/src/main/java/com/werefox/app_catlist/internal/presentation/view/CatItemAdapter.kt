package com.werefox.app_catlist.internal.presentation.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.werefox.app_catlist.databinding.ItemCatBinding
import com.werefox.core_domain.entity.Cat

class CatItemAdapter() :
    RecyclerView.Adapter<CatItemAdapter.ViewHolder>() {

    class ViewHolder(val viewBinding: ItemCatBinding) :
        RecyclerView.ViewHolder(viewBinding.root)

    private val models: MutableList<Cat> = mutableListOf()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemCatBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder.viewBinding) {
            val catItem = models[position]
            Picasso.get().load(catItem.imageUrl).into(imageCatItem)
        }
    }

    override fun getItemCount(): Int {
        return models.size
    }

    fun update(
        models: List<Cat>
    ) {
        this.models.clear()
        this.models.addAll(models)
    }
}