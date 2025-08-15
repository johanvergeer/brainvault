package com.brainvault.utils.units

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import kotlin.math.PI

class AngularVelocityTest {

    @Test
    fun `ofRadPerSec stores the value in radsec`() {
        val omega = AngularVelocity.ofRadPerSec(10.0)
        assertEquals(10.0, omega.inRadPerSec)
    }

    @Test
    fun `ofDegreePerSec converts degsec to radsec`() {
        val omega = AngularVelocity.ofDegreePerSec(180.0) // 180°/s = π rad/s
        assertEquals(PI, omega.inRadPerSec, 1e-9)
    }

    @Test
    fun `ofRpm converts rpm to radsec`() {
        // 60 rpm = 2π rad/s
        val omega = AngularVelocity.ofRpm(60.0)
        assertEquals(2 * PI, omega.inRadPerSec, 1e-9)
    }

    @Test
    fun `inDegreePerSec converts radsec to degsec`() {
        val omega = AngularVelocity.ofRadPerSec(PI)
        assertEquals(180.0, omega.inDegreePerSec, 1e-9)
    }

    @Test
    fun `inRpm converts radsec to rpm`() {
        val omega = AngularVelocity.ofRadPerSec(2 * PI) // 2π rad/s = 60 rpm
        assertEquals(60.0, omega.inRpm, 1e-9)
    }

    @Test
    fun `dsl radPerSec`() {
        val omega = 5.0.radPerSec
        assertEquals(5.0, omega.inRadPerSec)
    }

    @Test
    fun `dsl degreePerSec`() {
        val omega = 180.0.degreePerSec
        assertEquals(PI, omega.inRadPerSec, 1e-9)
    }

    @Test
    fun `dsl rpm`() {
        val omega = 60.0.rpm
        assertEquals(2 * PI, omega.inRadPerSec, 1e-9)
    }
}