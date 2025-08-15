package com.brainvault.utils.units

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class TorqueTest {

    @Test
    fun `ofNm stores the value as Nm`() {
        val torque = Torque.ofNm(5.0)
        assertEquals(5.0, torque.inNm)
    }

    @Test
    fun `inNmm converts Nm to Nmm`() {
        val torque = Torque.ofNm(2.0)  // 2.0 Nm = 2000 Nmm
        assertEquals(2000.0, torque.inNmm)
    }

    @Test
    fun `plus adds two torques`() {
        val a = 3.0.Nm
        val b = 2.0.Nm
        val result = a + b
        assertEquals(5.0, result.inNm)
    }

    @Test
    fun `minus subtracts two torques`() {
        val a = 5.0.Nm
        val b = 2.0.Nm
        val result = a - b
        assertEquals(3.0, result.inNm)
    }

    @Test
    fun `times multiplies a torque by a scalar`() {
        val torque = 2.0.Nm
        val result = torque * 4.0
        assertEquals(8.0, result.inNm)
    }

    @Test
    fun `div divides a torque by a scalar`() {
        val torque = 8.0.Nm
        val result = torque / 2.0
        assertEquals(4.0, result.inNm)
    }

    @Test
    fun `dsl Nm creates a torque from Double`() {
        val torque = 10.0.Nm
        assertEquals(10.0, torque.inNm)
    }

    @Test
    fun `dsl Nm creates a torque from Int`() {
        val torque = 3.Nm
        assertEquals(3.0, torque.inNm)
    }
}