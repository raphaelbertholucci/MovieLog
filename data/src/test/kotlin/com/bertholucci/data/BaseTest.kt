package com.bertholucci.data

import io.mockk.MockKAnnotations
import org.junit.Before

abstract class BaseTest<T : Any> {

    lateinit var agent: T

    abstract fun init()

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        init()
    }
}