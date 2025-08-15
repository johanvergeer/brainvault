package com.brainvault.utils.mechanics

import com.brainvault.utils.units.*
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import kotlin.math.PI

class TorqueHelpersTest {

    @Test
    fun `requiredTorque is J times alpha`() {
        val J = 2.0.kgM2
        val alpha = 3.0.radPerSec2
        val result = J.requiredTorque(alpha)
        assertEquals(6.0, result.inNm, 1e-9)
    }

    @Test
    fun `requiredTorqueForRamp uses alpha = omega_t`() {
        // ω = 2π rad/s (== 60 rpm), ramp in 1 s → α = 2π
        val J = 1.0.kgM2
        val target = 60.0.rpm
        val torque = J.requiredTorqueForRamp(target, 1.0)
        assertEquals(2*PI, torque.inNm, 1e-9)
    }

    @Test
    fun `requiredTorqueForScrew converts linear acceleration to angular`() {
        // a=1 m/s², p=1 m → α = 2π → τ = J·α
        val J = 1.0.kgM2
        val torque = J.requiredTorqueForScrew(1.0.mps2, 1.0.m.length)
        assertEquals(2 * PI, torque.inNm, 1e-9)
    }

    @Test
    fun `requiredTorqueForPulley converts linear acceleration to angular`() {
        // r = 1 m → α = 1/1 =1 → τ = J
        val J = 2.0.kgM2
        val torque = J.requiredTorqueForPulley(1.0.mps2, 1.0.m.radius)
        assertEquals(2.0, torque.inNm, 1e-9)
    }

    @Test
    fun `forceToTorqueForScrew uses F × (lead_2pi_eff)`() {
        val F = 10.0.N
        val lead = 0.01.m.length       // 10 mm lead
        val efficiency = 1.0
        // τ = F * (p / (2π)) = 10 * (0.01 / (2π))
        val expected = 10.0 * (0.01 / (2 * PI))
        val torque = F.toTorqueForScrew(lead, efficiency)
        assertEquals(expected, torque.inNm, 1e-9)
    }

    @Test
    fun `torque multiplied by gear ratio`() {
        val t = 5.0.Nm
        val ratio = 3.0.ratio
        val result = t * ratio
        assertEquals(15.0, result.inNm, 1e-9)
    }

    @Test
    fun `MomentOfInertia times AngularAcceleration equals torque`() {
        val J = 2.0.kgM2
        val alpha = 3.0.radPerSec2

        val torque = J * alpha

        assertEquals(6.0, torque.inNm, 1e-9)
    }
}