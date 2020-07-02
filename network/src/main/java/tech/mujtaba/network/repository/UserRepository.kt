package tech.mujtaba.network.repository

import io.reactivex.Single
import tech.mujtaba.network.model.external.User
import tech.mujtaba.network.model.external.UserAlbum

interface UserRepository {
    fun getUsers(): Single<List<User>>
    fun getUserAlbum(userId: Int): Single<List<UserAlbum>>
}