package com.brainvault.utils.mechanics

import com.brainvault.utils.units.*
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test
import kotlin.math.PI
import kotlin.time.Duration.Companion.milliseconds
import kotlin.time.Duration.Companion.seconds

class KinematicsTest {

    @Test
    fun `linear to angular acceleration for screw uses 2pi_over_lead`() {
        val a = 0.5.mps2
        val lead = 5.0.mm.length         // 0.005 m
        val expected = a.inMetersPerSec2 * (2 * PI / 0.005)
        val result = a.toAngularForScrew(lead)
        assertEquals(expected, result.inRadPerSec2, 1e-9)
    }

    @Test
    fun `linear to angular acceleration for pulley uses a_over_r`() {
        val a = 1.0.mps2
        val r = 50.0.mm.radius           // 0.05 m
        val expected = 1.0 / 0.05
        val result = a.toAngularForPulley(r)
        assertEquals(expected, result.inRadPerSec2, 1e-9)
    }

    @Test
    fun `requiredAngularAccelerationForRamp computes alpha correctly`() {
        val omegaTarget = 3000.0.rpm     // inRadPerSec
        val seconds = 1.0
        val expected = omegaTarget.inRadPerSec / seconds
        val result = requiredAngularAccelerationForRamp(omegaTarget, seconds)
        assertEquals(expected, result.inRadPerSec2, 1e-9)
    }

    @Test
    fun `Velocity toAngularForScrew converts linVel to angularVel`() {
        // v = 60000 mm/min = 1 m/s
        // lead = 10 mm → 6000 rpm → 628.318 rad/s
        val v = 60000.mmPerMinute
        val lead = 10.0.mm.length
        val omega = v.toAngularForScrew(lead)
        val expectedRpm = 60000.0 / 10.0 // 6000 rpm
        val expectedRadPerSec = expectedRpm * (2 * PI) / 60
        assertEquals(expectedRadPerSec, omega.inRadPerSec, 1e-9)
    }

    @Test
    fun `angular velocity multiplied by ratio`() {
        val omega = 100.0.radPerSec
        val result = omega * 2.0.ratio
        assertEquals(200.0, result.inRadPerSec, 1e-9)
    }

    @Test
    fun `angular velocity divided by ratio`() {
        val omega = 100.0.radPerSec
        val result = omega / 2.0.ratio
        assertEquals(50.0, result.inRadPerSec, 1e-9)
    }

    @Test
    fun `mass times acceleration gives force`() {
        val m = 2.0.kg
        val a = 3.0.mps2
        val F = m * a
        assertEquals(6.0, F.newton, 1e-9)
    }

    @Test
    fun `linear to screw throws on zero lead`() {
        val a = 1.0.mps2
        assertThrows(IllegalArgumentException::class.java) {
            a.toAngularForScrew(0.0.m.length)
        }
    }

    @Test
    fun `linear to pulley throws on zero radius`() {
        val a = 1.0.mps2
        assertThrows(IllegalArgumentException::class.java) {
            a.toAngularForPulley(0.0.m.radius)
        }
    }

        @Test
    fun `dividing angular velocity by duration gives angular acceleration`() {
        // 6.283 rad/s / 1 s = 6.283 rad/s²
        val omega = 2 * PI // rad/s
        val duration = 1.seconds
        val alpha = omega.radPerSec / duration
        assertEquals(2 * PI, alpha.inRadPerSec2, 1e-9)
    }

    @Test
    fun `duration can be fractional (e g 0_5s)`() {
        // 6.283 rad/s / 0.5 s = 12.566 rad/s²
        val omega = 2 * PI
        val duration = 500.milliseconds
        val alpha = omega.radPerSec / duration
        assertEquals(2 * PI / 0.5, alpha.inRadPerSec2, 1e-9)
    }
}
