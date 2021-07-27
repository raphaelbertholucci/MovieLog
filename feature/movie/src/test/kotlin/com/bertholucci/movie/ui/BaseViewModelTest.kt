package com.bertholucci.movie.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import io.mockk.MockKAnnotations
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.rules.TestRule

abstract class BaseViewModelTest<T : Any> {

    @ExperimentalCoroutinesApi
    @get:Rule
    val rule = MainCoroutineRule()

    @get:Rule
    var instantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    lateinit var viewModel: T

    abstract fun init()

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        init()
    }
}