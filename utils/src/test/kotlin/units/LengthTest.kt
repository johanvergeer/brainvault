package com.brainvault.utils.units

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class LengthTest {

    @Test
    fun `ofMeters stores the value in meters`() {
        val length = Length.ofMeters(2.0)
        assertEquals(2.0, length.inMeters)
    }

    @Test
    fun `ofMillimeters converts mm to meters`() {
        val length = Length.ofMillimeters(500.0)
        assertEquals(0.5, length.inMeters)
    }

    @Test
    fun `inMillimeters converts back to mm`() {
        val length = Length.ofMeters(1.5)
        assertEquals(1500.0, length.inMillimeters)
    }

    @Test
    fun `plus adds two lengths`() {
        val a = 1.0.m.length
        val b = 0.5.m.length
        val result = a + b
        assertEquals(1.5, result.inMeters)
    }

    @Test
    fun `minus subtracts two lengths`() {
        val a = 2.0.m.length
        val b = 0.5.m.length
        val result = a - b
        assertEquals(1.5, result.inMeters)
    }

    @Test
    fun `times multiplies a length by a scalar`() {
        val length = 1.5.m.length
        val result = length * 3.0
        assertEquals(4.5, result.inMeters)
    }

    @Test
    fun `div divides a length by a scalar`() {
        val length = 2.0.m.length
        val result = length / 2.0
        assertEquals(1.0, result.inMeters)
    }

    @Test
    fun `dsl m creates a Length from Double`() {
        val length = 0.25.m.length
        assertEquals(0.25, length.inMeters)
    }

    @Test
    fun `dsl mm creates a Length from Double`() {
        val length = 500.0.mm.length
        assertEquals(0.5, length.inMeters)
    }

    @Test
    fun `dsl m works for Int`() {
        val length = 2.m.length
        assertEquals(2.0, length.inMeters)
    }

    @Test
    fun `dsl mm works for Int`() {
        val length = 250.mm.length
        assertEquals(0.25, length.inMeters)
    }
}