package tech.mujtaba.network.service

import io.reactivex.Single
import retrofit2.http.GET
import tech.mujtaba.network.model.internal.AlbumResponse
import tech.mujtaba.network.model.internal.UserResponse

internal interface NetworkService {
    @GET("users")
    fun getUsers(): Single<List<UserResponse>>

    @GET("photos")
    fun getAlbums(): Single<List<AlbumResponse>>
}