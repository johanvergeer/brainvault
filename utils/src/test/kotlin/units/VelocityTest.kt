package com.brainvault.utils.units

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class VelocityTest {

    @Test
    fun `ofMetersPerSecond stores the value in mps`() {
        val velocity = Velocity.ofMetersPerSecond(2.0)
        assertEquals(2.0, velocity.inMetersPerSec)
    }

    @Test
    fun `ofMillimetersPerMinute converts mmmin to mps`() {
        // 60000 mm/min = 1 m/s
        val velocity = Velocity.ofMillimetersPerMinute(60_000.0)
        assertEquals(1.0, velocity.inMetersPerSec, 1e-9)
    }

    @Test
    fun `inMillimetersPerMinute converts mps to mmmin`() {
        // 2.0 m/s = 120000 mm/min
        val velocity = Velocity.ofMetersPerSecond(2.0)
        assertEquals(120_000.0, velocity.inMillimetersPerMinute, 1e-9)
    }

    @Test
    fun `dsl mps creates a velocity from Double`() {
        val velocity = 3.0.mps
        assertEquals(3.0, velocity.inMetersPerSec)
    }

    @Test
    fun `dsl mmPerMinute creates a velocity and converts to mps`() {
        val velocity = 60_000.mmPerMinute  // 1 m/s
        assertEquals(1.0, velocity.inMetersPerSec, 1e-9)
    }
}