package com.example.unit_test.ui.home

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.unit_test.App
import com.example.unit_test.api.NetworkService
import com.example.unit_test.data.UserRepository
import com.example.unit_test.model.UserProfile
import dagger.hilt.android.testing.CustomTestApplication
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
import getOrAwaitValue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.whenever
import org.robolectric.RobolectricTestRunner
import javax.inject.Inject

//@RunWith(MockitoJUnitRunner::class)
@RunWith(AndroidJUnit4::class)
@HiltAndroidTest
//@CustomTestApplication(App::class)
class HomeViewModel2Test {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    // Mock dependencies
    @Inject
    lateinit var userRepository: UserRepository

    @Inject
    lateinit var networkService: NetworkService

    // Class under test
    private lateinit var homeViewModel: HomeViewModel

    @Before
    fun setUp() {
        hiltRule.inject()
        homeViewModel = HomeViewModel(userRepository)
    }

    @After
    fun tearDown() {
    }

    @Test
    fun `loginUser success`() = runBlockingTest {
        // Arrange
        val username = "testUser"
        val password = "testPassword"
        val expectedResult = true

        // Mock the network service's behavior
        whenever(networkService.performLoginRequest(username, password)).thenReturn(expectedResult)

        // Set up Dagger Hilt to provide the mocked dependencies
//        HiltTestApplication.getInstance().component.apply {
//            userRepository = this@HomeViewModel2Test.userRepository
//            networkService = this@HomeViewModel2Test.networkService
//        }

        // Act
        homeViewModel.loginUser(username, password)

        // Assert
        val result = homeViewModel.loginResult.getOrAwaitValue()
        Assert.assertTrue(result) // Assert that login was successful
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