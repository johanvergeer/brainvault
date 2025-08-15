package com.brainvault.utils.units

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class ForceTest {

    @Test
    fun `dsl N creates a force in Newtons`() {
        val f = 50.0.N
        assertEquals(50.0, f.newton)
    }

    @Test
    fun `dsl kN creates a force in kiloNewtons`() {
        val f = 2.0.kN     // 2 kN = 2000 N
        assertEquals(2000.0, f.newton)
    }

    @Test
    fun `dsl N works for Int`() {
        val f = 75.N
        assertEquals(75.0, f.newton)
    }

    @Test
    fun `dsl kN works for Int`() {
        val f = 3.kN       // 3 kN = 3000 N
        assertEquals(3000.0, f.newton)
    }

    @Test
    fun `plus adds two forces`() {
        val f1 = 100.0.N
        val f2 = 1.0.kN
        val result = f1 + f2
        assertEquals(1100.0, result.newton)
    }

    @Test
    fun `minus subtracts two forces`() {
        val f1 = 2.0.kN
        val f2 = 500.0.N
        val result = f1 - f2
        assertEquals(1500.0, result.newton)
    }

    @Test
    fun `times multiplies force by a scalar`() {
        val f = 200.0.N
        val result = f * 3.0
        assertEquals(600.0, result.newton)
    }

    @Test
    fun `div divides force by a scalar`() {
        val f = 300.0.N
        val result = f / 3.0
        assertEquals(100.0, result.newton)
    }

        @Test
    fun `toKnString formats force with default 0 decimals`() {
        val force = 1234.56.N   // 1.23456 kN
        val result = force.toKnString()
        assertEquals("1 kN", result)
    }

    @Test
    fun `toKnString formats force with given number of decimals`() {
        val force = 1234.56.N   // 1.23456 kN
        val result = force.toKnString(2)
        assertEquals("1.23 kN", result)
    }
}