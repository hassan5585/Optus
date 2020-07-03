package tech.mujtaba.optus.feature.picturedetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import tech.mujtaba.network.model.external.UserAlbum
import tech.mujtaba.network.repository.UserRepository
import tech.mujtaba.optus.viewmodel.BaseViewModel
import javax.inject.Inject

class PictureDetailsViewModel @Inject constructor(private val userRepository: UserRepository) : BaseViewModel() {

    val pictureLiveData: LiveData<UserAlbum>
        get() = internalPictureLiveData

    private val internalPictureLiveData = MutableLiveData<UserAlbum>()

    fun getUserPicture(albumId: Int, pictureId: Int) {
        addDisposable(userRepository.getUserAlbum(albumId).subscribe { albumList ->
            albumList.firstOrNull { it.id == pictureId }?.let {
                internalPictureLiveData.postValue(it)
            }
        })
    }

}