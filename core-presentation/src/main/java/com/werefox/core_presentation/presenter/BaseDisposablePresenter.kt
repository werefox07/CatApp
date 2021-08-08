package com.werefox.core_presentation.presenter

import com.arellomobile.mvp.MvpPresenter
import com.arellomobile.mvp.MvpView
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * Holds [Disposable] object and perform disposal in onDestroy()
 */
abstract class BaseDisposablePresenter<T : MvpView> protected constructor() : MvpPresenter<T>() {

    var compositeDisposable: CompositeDisposable = CompositeDisposable()

    override fun onDestroy() {
        compositeDisposable.clear()
        super.onDestroy()
    }

    protected fun Disposable.unsubscribeOnDestroy() = apply { compositeDisposable.add(this) }
}