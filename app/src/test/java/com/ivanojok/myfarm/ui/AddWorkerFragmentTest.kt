package com.ivanojok.myfarm.ui

import org.junit.Assert.*

import org.junit.Test

class AddWorkerFragmentTest {

    @Test
    fun checkUserInput() {
        val x = AddWorkerFragment().checkUserInput("My name is Ivan")
        val y = AddWorkerFragment().checkUserInput("9999999")
        val z = AddWorkerFragment().checkUserInput("yewfwefwdc")
        assertEquals(x, "Name has been found")
        assertEquals(y, "Digits Found")
        assertEquals(z, "Nothing Found")

        //assertThat()
    }
}