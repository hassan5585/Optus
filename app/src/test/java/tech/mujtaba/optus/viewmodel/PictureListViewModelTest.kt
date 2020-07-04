package tech.mujtaba.optus.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import io.reactivex.Single
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is.`is`
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import tech.mujtaba.network.model.external.UserAlbum
import tech.mujtaba.network.repository.UserRepository
import tech.mujtaba.optus.feature.picturelist.PictureListViewModel

class PictureListViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val mockRepository = Mockito.mock(UserRepository::class.java)

    private val mockAlbum = Mockito.mock(UserAlbum::class.java)

    @Before
    fun setup() {
        Mockito.`when`(mockRepository.getUserAlbum(1)).thenReturn(Single.just(listOf(mockAlbum, mockAlbum)))
    }

    @Test
    fun `given a userId when repository provides a list of albums for the userId the viewModel should push the list to live data`() {
        val viewModel = PictureListViewModel(mockRepository)
        viewModel.getUserAlbums(1)
        assertThat(viewModel.albumLiveData.value?.size, `is`(equalTo(2)))
    }

}