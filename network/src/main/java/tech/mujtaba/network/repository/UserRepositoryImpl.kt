package tech.mujtaba.network.repository

import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import tech.mujtaba.network.model.external.User
import tech.mujtaba.network.model.external.UserAlbum
import tech.mujtaba.network.model.internal.AlbumResponse
import tech.mujtaba.network.model.internal.UserResponse
import tech.mujtaba.network.service.NetworkService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class UserRepositoryImpl @Inject constructor(private val service: NetworkService) : UserRepository {

    private val userList: MutableList<UserResponse> = mutableListOf()
    private val albumList: MutableList<AlbumResponse> = mutableListOf()

    override fun getUsers(): Single<List<User>> {
        return Single.defer {
            val userObservable = if (userList.isNotEmpty()) {
                Single.just(userList)
            } else {
                service.getUsers().doOnSuccess {
                    userList.clear()
                    userList.addAll(it)
                }
            }
            userObservable.map {
                it.map { mapToExternalUser(it) }
            }
        }.subscribeOn(Schedulers.io())
                .onErrorReturnItem(listOf())
    }

    override fun getUserAlbum(userId: Int): Single<List<UserAlbum>> {
        return Single.defer {
            val albumObservable = if (albumList.isNotEmpty()) {
                Single.just(albumList)
            } else {
                service.getAlbums().doOnSuccess {
                    albumList.clear()
                    albumList.addAll(it)
                }
            }.map {
                it.map { mapToExternalAlbum(it) }
            }
            albumObservable.map {
                it.filter { it.albumId == userId }
            }
        }.subscribeOn(Schedulers.io())
                .onErrorReturnItem(listOf())
    }

    private fun mapToExternalUser(internalUser: UserResponse): User = User(internalUser.id, internalUser.name, internalUser.email, internalUser.phone)

    private fun mapToExternalAlbum(internalAlbum: AlbumResponse): UserAlbum = UserAlbum(internalAlbum.albumId, internalAlbum.title, internalAlbum.url, internalAlbum.thumbnailUrl)
}