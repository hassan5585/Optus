package tech.mujtaba.optus.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import tech.mujtaba.optus.feature.picturedetails.PictureDetailsViewModel
import tech.mujtaba.optus.feature.picturelist.PictureListViewModel
import tech.mujtaba.optus.feature.userlist.UserListViewModel
import tech.mujtaba.optus.viewmodel.ViewModelFactory
import tech.mujtaba.optus.viewmodel.ViewModelKey

@Module
abstract class ViewModelModule {
    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(PictureDetailsViewModel::class)
    abstract fun bindPictureDetailsVM(viewModel: PictureDetailsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(UserListViewModel::class)
    abstract fun bindUserListVM(viewModel: UserListViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(PictureListViewModel::class)
    abstract fun bindPictureListVM(viewModel: PictureListViewModel): ViewModel
}