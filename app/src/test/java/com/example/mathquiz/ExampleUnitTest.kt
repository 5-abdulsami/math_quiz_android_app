package com.example.mathquiz

import org.junit.Test

import org.junit.Assert.*



/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun testGetQuestionZero() {
        val question = getQuestion(0)
        assertEquals("What is the result of 20 + 77?", question)
    }

    @Test
    fun testGetQuestionOutOfBounds() {
        val question = getQuestion(10)
        assertEquals("", question)
    }
}

