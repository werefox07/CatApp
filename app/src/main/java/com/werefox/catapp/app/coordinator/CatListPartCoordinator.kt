package com.werefox.catapp.app.coordinator

import com.github.terrakok.cicerone.Router
import com.werefox.feature_catlist.output.CatListOutput
import com.werefox.feature_favorites.internal.presentation.view.FavoritesScreen

class CatListPartCoordinator(private val router: Router) : CatListOutput {

    override fun openFavoritesScreen() {
        router.navigateTo(
            FavoritesScreen()
        )
    }
}