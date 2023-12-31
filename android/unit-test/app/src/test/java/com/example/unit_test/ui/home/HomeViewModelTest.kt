package com.example.unit_test.ui.home

import com.example.unit_test.data.UserRepository
import com.example.unit_test.model.UserProfile
import getOrAwaitValue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.whenever
import org.robolectric.RobolectricTestRunner

//@RunWith(MockitoJUnitRunner::class)
@RunWith(RobolectricTestRunner::class)
class HomeViewModelTest {

    // Mock dependencies
    @Mock
    private lateinit var userRepository: UserRepository

    // Class under test
    private lateinit var homeViewModel: HomeViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        homeViewModel = HomeViewModel(userRepository)
    }

    @After
    fun tearDown() {
    }

    @Test
    fun `fetch user`() = runTest {
        // Arrange
        val uId = "123"
        val username = "testUser"
        val email = "testPassword"

        // Mock the repository's behavior
        whenever(userRepository.getUserProfile(uId)).thenReturn(UserProfile(uId,username,email))

        // Act
        homeViewModel.fetchUserProfile(uId)

        // Assert
        val result = homeViewModel.userProfile.getOrAwaitValue()
        Assert.assertEquals(result.username, username ) // Assert that login was successful
    }

}