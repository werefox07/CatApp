package com.werefox.app_catlist.internal.presentation.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.werefox.app_catlist.databinding.ItemCatBinding
import com.werefox.core_domain.entity.CatEntity

class CatItemAdapter(/*val resourceManager: ResourceManager*/) :
    RecyclerView.Adapter<CatItemAdapter.ViewHolder>() {

    class ViewHolder(val viewBinding: ItemCatBinding) :
        RecyclerView.ViewHolder(viewBinding.root)

    private val models: MutableList<CatEntity> = mutableListOf()


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
            tvCategory.text = catItem.id
            catItem.breeds?.let { breeds ->
                if (breeds.isNotEmpty()) {
                    tvCategory.text =
                        breeds.first() //todo 1 - выводить строку, разобраться с отстутвующим полем и ошибкой
                }
            }
            Picasso.get().load(catItem.url).into(imageCatItem)
        }
    }

    override fun getItemCount(): Int {
        return models.size
    }

    fun update(models: List<CatEntity>) {
        this.models.clear()
        this.models.addAll(models)
        notifyDataSetChanged()
    }
}