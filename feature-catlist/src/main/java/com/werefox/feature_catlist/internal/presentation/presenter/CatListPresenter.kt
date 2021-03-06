package com.werefox.feature_catlist.internal.presentation.presenter

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import com.werefox.core_domain.entity.CatEntity
import com.werefox.core_domain.interactor.EmptyParams
import com.werefox.core_domain.uihelper.ResourceManager
import com.werefox.core_domain.usecase.GetCatsUseCase
import com.werefox.core_domain.usecase.SaveImageUseCase
import com.werefox.core_presentation.presenter.BaseDisposablePresenter
import com.werefox.feature_catlist.R
import com.werefox.feature_catlist.internal.presentation.view.CatListView
import com.werefox.feature_catlist.output.CatListOutput
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import moxy.InjectViewState
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.OutputStream
import javax.inject.Inject


@InjectViewState
class CatListPresenter @Inject constructor(
    private val getCatsUseCase: GetCatsUseCase,
    private val resourceManager: ResourceManager,
    private val saveImageUseCase: SaveImageUseCase,
    private val catListOutput: CatListOutput,
    private val filePath: File?
) : BaseDisposablePresenter<CatListView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        getCatsUseCase.buildUseCaseSingle(EmptyParams)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { catsImages ->
                    viewState.showImages(catsImages)
                    viewState.setLoaderVisibility(false)
                },
                Throwable::printStackTrace
            )
            .unsubscribeOnDestroy()
    }

    private fun retryRequestCats() {
        getCatsUseCase.buildUseCaseSingle(EmptyParams)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { catsImages ->
                    viewState.addImages(catsImages)
                    viewState.setLoaderVisibility(false)
                    viewState.setLoadInProgress(false)
                },
                Throwable::printStackTrace
            )
            .unsubscribeOnDestroy()
    }

    override fun attachView(view: CatListView?) {
        super.attachView(view)
    }

    fun addToFavorite(cat: CatEntity, title: String) {
        saveImageUseCase.buildUseCaseCompletable(Pair(cat, title))
            .subscribeOn(Schedulers.io())
            .subscribe({}, { it.printStackTrace() })
    }

    fun saveImageToExternalStorage(drawable: Drawable, id: String) {
        val bitmap = (drawable as BitmapDrawable).bitmap
        val fileName = "$id${resourceManager.getString(R.string.cat_item_file_extension)}"
        val file = File(filePath, fileName)
        if (!file.exists()) {
            try {
                val stream: OutputStream = FileOutputStream(file)
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream)
                stream.flush()
                stream.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        } else {
            viewState.showToast(resourceManager.getString(R.string.cat_item_file_already_exist))
        }
    }

    fun onFavoritesClick() {
        catListOutput.openFavoritesScreen()
    }

    fun onScrolledToBottom() {
        viewState.setLoaderVisibility(true)
        retryRequestCats()
    }
}