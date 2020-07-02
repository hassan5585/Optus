package tech.mujtaba.optus.di

import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import tech.mujtaba.optus.viewmodel.ViewModelFactory

@Module
abstract class ViewModelModule {
    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}