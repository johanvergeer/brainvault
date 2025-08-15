package com.brainvault.utils.mechanics

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test

class GearRatioTest {

    @Test
    fun `dsl ratio on Double creates a gear ratio`() {
        val gr = 2.0.ratio
        assertEquals(2.0, gr.ratio)
    }

    @Test
    fun `dsl ratio on Int creates a gear ratio`() {
        val gr = 4.ratio
        assertEquals(4.0, gr.ratio)
    }

    @Test
    fun `zero or negative gear ratio throws`() {
        assertThrows(IllegalArgumentException::class.java) { (-1.0).ratio }
        assertThrows(IllegalArgumentException::class.java) { 0.0.ratio }
    }
}