package com.werefox.feature_catlist.di

import android.content.Context
import android.os.Environment
import com.squareup.picasso.Picasso
import dagger.Module
import dagger.Provides
import java.io.File

@Module
class CatListModule {

    @CatListScope
    @Provides
    fun getFilePath(appContext: Context): File? =
        appContext.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS)

    @CatListScope
    @Provides
    fun getPicasso(): Picasso = Picasso.get()
}