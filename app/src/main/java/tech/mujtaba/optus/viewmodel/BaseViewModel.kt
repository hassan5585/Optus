package tech.mujtaba.optus.viewmodel

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BaseViewModel : ViewModel() {
    private val disposableBag by lazy {
        CompositeDisposable()
    }

    protected fun addDisposable(disposable: Disposable) {
        disposableBag.add(disposable)
    }

    override fun onCleared() {
        disposableBag.clear()
    }
}