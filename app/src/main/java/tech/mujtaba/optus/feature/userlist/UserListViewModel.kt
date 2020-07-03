package tech.mujtaba.optus.feature.userlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import tech.mujtaba.network.model.external.User
import tech.mujtaba.network.repository.UserRepository
import tech.mujtaba.optus.viewmodel.BaseViewModel
import javax.inject.Inject

class UserListViewModel @Inject constructor(userRepository: UserRepository) : BaseViewModel() {

    val userLiveData: LiveData<List<User>>
        get() = internalUserLiveData

    private val internalUserLiveData = MutableLiveData<List<User>>()

    init {
        addDisposable(userRepository.getUsers().subscribe { userList ->
            internalUserLiveData.postValue(userList)
        })
    }
}