package tech.mujtaba.optus.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import io.reactivex.Single
import org.hamcrest.CoreMatchers.*
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.*
import tech.mujtaba.network.model.external.User
import tech.mujtaba.network.repository.UserRepository
import tech.mujtaba.optus.feature.userlist.UserListViewModel

class UserListViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val mockRepository = mock(UserRepository::class.java)

    private val mockUser = mock(User::class.java)

    @Before
    fun setup() {
        `when`(mockRepository.getUsers()).thenReturn(Single.just(listOf(mockUser, mockUser)))
    }

    @Test
    fun `given repository provides a list of users the viewModel should push the list to live data on Instantiation`() {
        val viewModel = UserListViewModel(mockRepository)
        assertThat(viewModel.userLiveData.value?.size ?: 0, `is`(equalTo(2)))
    }
}