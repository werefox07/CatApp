package com.werefox.feature_favorites.internal.presentation.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.werefox.core_domain.entity.CatFavoriteEntity
import com.werefox.core_domain.uihelper.ResourceManager
import com.werefox.feature_favorites.R
import com.werefox.feature_favorites.databinding.ItemFavoriteBinding

class FavoriteItemAdapter(
    private val resourceManager: ResourceManager
) :
    RecyclerView.Adapter<FavoriteItemAdapter.ViewHolder>() {

    class ViewHolder(val viewBinding: ItemFavoriteBinding) :
        RecyclerView.ViewHolder(viewBinding.root)

    private val models: MutableList<CatFavoriteEntity> = mutableListOf()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemFavoriteBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder.viewBinding) {
            val favoriteItem = models[position]
            val text = "${resourceManager.getString(R.string.category_title)} ${favoriteItem.title}"
            tvFavoriteCategory.text = text
            Picasso.get().load(favoriteItem.url).into(imageFavoriteItem)
        }
    }

    override fun getItemCount(): Int {
        return models.size
    }

    fun update(models: List<CatFavoriteEntity>) {
        this.models.clear()
        this.models.addAll(models)
        notifyDataSetChanged()
    }
}