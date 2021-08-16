package com.werefox.catapp.app.coordinator

import com.github.terrakok.cicerone.Router
import com.werefox.feature_favorites.output.FavoritesOutput

class FavoritesPartCoordinator(private val router: Router) : FavoritesOutput {

    override fun onBackPressed() {
        router.exit()
    }
}