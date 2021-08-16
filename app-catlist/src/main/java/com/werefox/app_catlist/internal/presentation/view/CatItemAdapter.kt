package com.werefox.app_catlist.internal.presentation.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.werefox.app_catlist.R
import com.werefox.app_catlist.databinding.ItemCatBinding
import com.werefox.core_domain.entity.CatEntity
import com.werefox.core_domain.uihelper.ResourceManager

class CatItemAdapter(
    val actionListener: CatItemActionListener,
    val resourceManager: ResourceManager,
) :
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
            var text =
                "${resourceManager.getString(R.string.cat_item_title)} ${resourceManager.getString(R.string.cat_item_title_no_category)}"
            catItem.categories?.let { categories ->
                if (categories.isNotEmpty()) {
                    text =
                        "${resourceManager.getString(R.string.cat_item_title)} ${categories.first()}"
                }
            }
            tvCategory.text = text
            Picasso.get().load(catItem.url).into(imageCatItem)
            btnAddToFavorite.setOnClickListener { actionListener.onClickAddToFavorite(catItem.url) }
            btnSave.setOnClickListener {
                actionListener.onClickSave(imageCatItem.drawable,
                    catItem.id)
            }
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