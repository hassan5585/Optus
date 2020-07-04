package tech.mujtaba.network

import io.reactivex.Single
import io.reactivex.observers.TestObserver
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.*
import tech.mujtaba.network.model.external.User
import tech.mujtaba.network.model.external.UserAlbum
import tech.mujtaba.network.model.internal.AlbumResponse
import tech.mujtaba.network.model.internal.UserResponse
import tech.mujtaba.network.repository.UserRepositoryImpl
import tech.mujtaba.network.rule.RxRule
import tech.mujtaba.network.service.NetworkService

class UserRepositoryImplTest {

    private companion object {
        const val EMPTY_STRING = ""
    }

    @get:Rule
    val rxRule = RxRule()

    private val mockService = mock(NetworkService::class.java)

    private val mockAlbumResponse = mock(AlbumResponse::class.java)

    private val mockUserResponse = mock(UserResponse::class.java)

    @Before
    fun setup() {
        `when`(mockUserResponse.id).thenReturn(1)
        `when`(mockUserResponse.name).thenReturn(EMPTY_STRING)
        `when`(mockUserResponse.email).thenReturn(EMPTY_STRING)
        `when`(mockUserResponse.phone).thenReturn(EMPTY_STRING)
        `when`(mockAlbumResponse.id).thenReturn(1)
        `when`(mockAlbumResponse.albumId).thenReturn(1)
        `when`(mockAlbumResponse.title).thenReturn(EMPTY_STRING)
        `when`(mockAlbumResponse.url).thenReturn(EMPTY_STRING)
        `when`(mockAlbumResponse.thumbnailUrl).thenReturn(EMPTY_STRING)
        `when`(mockService.getUsers()).thenReturn(Single.just(listOf(mockUserResponse, mockUserResponse)))
        `when`(mockService.getAlbums()).thenReturn(Single.just(listOf(mockAlbumResponse, mockAlbumResponse)))
    }

    @Test
    fun `given service returns a list of userResponse the repository should convert that to User object`() {
        val repository = UserRepositoryImpl(mockService)
        val testObserver = TestObserver<List<User>>()
        repository.getUsers().subscribe(testObserver)
        testObserver.assertNoErrors()
        testObserver.assertValueCount(1)
        assertThat(testObserver.values()[0].size, equalTo(2))
    }

    @Test
    fun `given service returns a list of albumResponse the repository should convert that to UserAlbum object`() {
        val repository = UserRepositoryImpl(mockService)
        val testObserver = TestObserver<List<UserAlbum>>()
        repository.getUserAlbum(1).subscribe(testObserver)
        testObserver.assertNoErrors()
        testObserver.assertValueCount(1)
        assertThat(testObserver.values()[0].size, equalTo(2))
    }

    @Test
    fun `given repository returns a list of user objects it should not ask the service twice`() {
        val repository = UserRepositoryImpl(mockService)
        val testObserver = TestObserver<List<User>>()
        repository.getUsers().subscribe(testObserver)
        repository.getUsers().subscribe(testObserver)
        repository.getUsers().subscribe(testObserver)
        repository.getUsers().subscribe(testObserver)
        verify(mockService, times(1)).getUsers()
    }

    @Test
    fun `given repository returns a list of album objects it should not ask the service twice`() {
        val repository = UserRepositoryImpl(mockService)
        val testObserver = TestObserver<List<UserAlbum>>()
        repository.getUserAlbum(1).subscribe(testObserver)
        repository.getUserAlbum(1).subscribe(testObserver)
        repository.getUserAlbum(1).subscribe(testObserver)
        repository.getUserAlbum(1).subscribe(testObserver)
        verify(mockService, times(1)).getAlbums()
    }
}