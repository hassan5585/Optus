package tech.mujtaba.optus.feature.picturelist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import tech.mujtaba.network.model.external.UserAlbum
import tech.mujtaba.network.repository.UserRepository
import tech.mujtaba.optus.viewmodel.BaseViewModel
import javax.inject.Inject

class PictureListViewModel @Inject constructor(private val userRepository: UserRepository) : BaseViewModel() {

    val albumLiveData: LiveData<List<UserAlbum>>
        get() = internalAlbumLiveData

    private val internalAlbumLiveData = MutableLiveData<List<UserAlbum>>()

    fun getUserAlbums(userId: Int) {
        addDisposable(userRepository.getUserAlbum(userId).subscribe { albumList ->
            internalAlbumLiveData.postValue(albumList)
        })
    }
}