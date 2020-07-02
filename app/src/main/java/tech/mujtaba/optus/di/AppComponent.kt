package tech.mujtaba.optus.di

import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import tech.mujtaba.network.di.NetworkModule
import tech.mujtaba.optus.OptusApp
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidSupportInjectionModule::class, NetworkModule::class, ViewModelModule::class])
interface AppComponent : AndroidInjector<OptusApp> {
    @Component.Factory
    interface Builder : AndroidInjector.Factory<OptusApp>
}