package com.brainvault.utils.units

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class MassTest {

    @Test
    fun `ofKilograms stores the value in kg`() {
        val mass = Mass.ofKilograms(5.0)
        assertEquals(5.0, mass.inKilograms)
    }

    @Test
    fun `ofGrams converts g to kg`() {
        val mass = Mass.ofGrams(250.0)
        assertEquals(0.25, mass.inKilograms)
    }

    @Test
    fun `inGrams converts back to g`() {
        val mass = Mass.ofKilograms(1.5)
        assertEquals(1500.0, mass.inGrams)
    }

    @Test
    fun `plus adds two masses`() {
        val a = 1.0.kg
        val b = 500.0.g
        val result = a + b
        assertEquals(1.5, result.inKilograms)
    }

    @Test
    fun `minus subtracts two masses`() {
        val a = 2.0.kg
        val b = 500.0.g
        val result = a - b
        assertEquals(1.5, result.inKilograms)
    }

    @Test
    fun `times multiplies a mass by a scalar`() {
        val mass = 2.0.kg
        val result = mass * 2.0
        assertEquals(4.0, result.inKilograms)
    }

    @Test
    fun `div divides a mass by a scalar`() {
        val mass = 2.0.kg
        val result = mass / 2.0
        assertEquals(1.0, result.inKilograms)
    }

    @Test
    fun `dsl kg creates a mass from Double`() {
        val mass = 5.0.kg
        assertEquals(5.0, mass.inKilograms)
    }

    @Test
    fun `dsl g creates a mass from Double`() {
        val mass = 250.0.g
        assertEquals(0.25, mass.inKilograms)
    }

    @Test
    fun `dsl kg works for Int`() {
        val mass = 3.kg
        assertEquals(3.0, mass.inKilograms)
    }

    @Test
    fun `dsl g works for Int`() {
        val mass = 500.g
        assertEquals(0.5, mass.inKilograms)
    }
}