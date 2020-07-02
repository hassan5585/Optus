package tech.mujtaba.optus.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import tech.mujtaba.optus.feature.picturedetails.PictureDetailsFragment
import tech.mujtaba.optus.feature.picturelist.PictureListFragment
import tech.mujtaba.optus.feature.userlist.UserListFragment

@Module
abstract class FragmentModule {

    @ContributesAndroidInjector
    abstract fun bindUserListFragment(): UserListFragment

    @ContributesAndroidInjector
    abstract fun bindAlbumListFragment(): PictureListFragment

    @ContributesAndroidInjector
    abstract fun bindAlbumDetailsFragment(): PictureDetailsFragment

}