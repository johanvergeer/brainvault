package com.brainvault.utils.units

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class AccelerationTest {

    @Test
    fun `ofMetersPerSec2 stores the value in mps2`() {
        val a = Acceleration.ofMetersPerSec2(2.0)
        assertEquals(2.0, a.inMetersPerSec2)
    }

    @Test
    fun `ofMillimetersPerSec2 converts mmps2 to mps2`() {
        // 2000 mm/s² == 2.0 m/s²
        val a = Acceleration.ofMillimetersPerSec2(2000.0)
        assertEquals(2.0, a.inMetersPerSec2)
    }

    @Test
    fun `inMillimetersPerSec2 converts back to mmps2`() {
        val a = Acceleration.ofMetersPerSec2(1.5)
        assertEquals(1500.0, a.inMillimetersPerSec2)
    }

    @Test
    fun `plus adds two accelerations`() {
        val a1 = Acceleration.ofMetersPerSec2(1.0)
        val a2 = Acceleration.ofMetersPerSec2(2.0)
        val result = a1 + a2
        assertEquals(3.0, result.inMetersPerSec2)
    }

    @Test
    fun `minus subtracts one acceleration from another`() {
        val a1 = Acceleration.ofMetersPerSec2(5.0)
        val a2 = Acceleration.ofMetersPerSec2(2.0)
        val result = a1 - a2
        assertEquals(3.0, result.inMetersPerSec2)
    }

    @Test
    fun `times multiplies an acceleration by a scalar`() {
        val a = Acceleration.ofMetersPerSec2(2.0)
        val result = a * 3.0
        assertEquals(6.0, result.inMetersPerSec2)
    }

    @Test
    fun `div divides an acceleration by a scalar`() {
        val a = Acceleration.ofMetersPerSec2(4.0)
        val result = a / 2.0
        assertEquals(2.0, result.inMetersPerSec2)
    }

    @Test
    fun `dsl mps2 creates an acceleration in mps2`() {
        val a = 3.0.mps2
        assertEquals(3.0, a.inMetersPerSec2)
    }

    @Test
    fun `dsl mmps2 creates an acceleration in mmps2 and converts internally to mps2`() {
        // 500 mm/s² = 0.5 m/s²
        val a = 500.mmps2
        assertEquals(0.5, a.inMetersPerSec2)
    }
}
