package tech.mujtaba.optus.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import io.reactivex.Single
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import tech.mujtaba.network.model.external.UserAlbum
import tech.mujtaba.network.repository.UserRepository
import tech.mujtaba.optus.feature.picturedetails.PictureDetailsViewModel

class PictureDetailsViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val mockRepository = mock(UserRepository::class.java)

    private val mockAlbumOne = mock(UserAlbum::class.java)

    private val mockAlbumTwo = mock(UserAlbum::class.java)

    @Before
    fun setup() {
        `when`(mockRepository.getUserAlbum(1)).thenReturn(Single.just(listOf(mockAlbumOne, mockAlbumTwo)))
        `when`(mockAlbumOne.id).thenReturn(2)
        `when`(mockAlbumTwo.id).thenReturn(3)
    }

    @Test
    fun `given a userId and pictureId when repository provides a list of pictures for that userId the viewModel should push the picture with provided pictureId`() {
        val viewModel = PictureDetailsViewModel(mockRepository)
        viewModel.getUserPicture(1, 2)
        assertThat(viewModel.pictureLiveData.value, equalTo(mockAlbumOne))
    }
}