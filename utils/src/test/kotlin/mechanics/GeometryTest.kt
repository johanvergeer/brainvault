package com.brainvault.utils.mechanics

import com.brainvault.utils.mechanics.*
import com.brainvault.utils.units.*
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import kotlin.math.PI
import kotlin.math.pow

class GeometryTest {

    @Test
    fun `diameter toRadius converts d to r`() {
        val d = 40.0.mm.diameter   // 40 mm
        val r = d.toRadius()
        assertEquals(0.02, r.meters, 1e-12) // 20 mm = 0.02 m
    }

    @Test
    fun `radius toDiameter converts r to d`() {
        val r = 25.0.mm.radius     // 25 mm
        val d = r.toDiameter()
        assertEquals(0.05, d.meters, 1e-12) // 50 mm = 0.05 m
    }

    @Test
    fun `diameter circumference uses π·d`() {
        val d = 100.0.mm.diameter       // 0.1 m
        assertEquals(PI * 0.1, d.circumference(), 1e-12)
    }

    @Test
    fun `radius circumference uses 2πr`() {
        val r = 50.0.mm.radius          // 0.05 m
        assertEquals(2 * PI * 0.05, r.circumference(), 1e-12)
    }

    @Test
    fun `diameter area uses π·(d2)²`() {
        val d = 80.0.mm.diameter
        val expected = PI * (0.08 / 2).pow(2)
        assertEquals(expected, d.area(), 1e-12)
    }

    @Test
    fun `radius area uses π·r²`() {
        val r = 40.0.mm.radius  // 0.04 m
        assertEquals(PI * 0.04 * 0.04, r.area(), 1e-12)
    }

    @Test
    fun `diameter plus adds lengths`() {
        val d1 = 20.0.mm.diameter
        val d2 = 10.0.mm.diameter
        assertEquals(0.03, (d1 + d2).meters, 1e-12)
    }

    @Test
    fun `radius minus subtracts lengths`() {
        val r1 = 40.0.mm.radius
        val r2 = 10.0.mm.radius
        assertEquals(0.03, (r1 - r2).meters, 1e-12)
    }

    @Test
    fun `Length extension creates diameter`() {
        val length = 60.0.mm.length
        val diameter = length.diameter
        assertEquals(0.06, diameter.meters, 1e-12)
    }

    @Test
    fun `Length extension creates radius`() {
        val length = 30.0.mm.length
        val radius = length.radius
        assertEquals(0.03, radius.meters, 1e-12)
    }
}