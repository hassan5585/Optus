package tech.mujtaba.optus

import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import tech.mujtaba.optus.di.DaggerAppComponent

class OptusApp : DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> = DaggerAppComponent.factory().create(this)
}