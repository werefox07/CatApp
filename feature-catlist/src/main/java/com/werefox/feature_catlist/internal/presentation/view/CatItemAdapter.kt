package com.werefox.feature_catlist.internal.presentation.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.werefox.feature_catlist.databinding.ItemCatBinding
import com.werefox.core_domain.entity.CatEntity
import com.werefox.core_domain.uihelper.ResourceManager
import com.werefox.feature_catlist.R

class CatItemAdapter(
    private val actionListener: CatItemActionListener,
    private val resourceManager: ResourceManager,
    private val picasso: Picasso,
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
            var categoryText =
                resourceManager.getString(R.string.cat_item_title_no_category)
            catItem.categories?.let { categories ->
                if (categories.isNotEmpty()) {
                    categoryText = categories.first()
                }
            }
            val text = "${resourceManager.getString(R.string.category_title)} $categoryText"
            tvCategory.text = text
            picasso.load(catItem.url).into(imageCatItem)
            btnAddToFavorite.setOnClickListener {
                actionListener.onClickAddToFavorite(catItem, categoryText)
            }
            btnDownload.setOnClickListener {
                actionListener.onClickDownloadImage(imageCatItem.drawable, catItem.id)
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

    fun add(models: List<CatEntity>) {
        this.models.addAll(models)
        notifyDataSetChanged()
    }
}