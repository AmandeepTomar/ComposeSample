package com.example.composesample

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
    fun recursion(){
        println(countNumber(10))
    }

    private fun countNumber(int: Int): Int {
        if (int==1){
            return 1
        }
        return countNumber(int-1)
    }
}