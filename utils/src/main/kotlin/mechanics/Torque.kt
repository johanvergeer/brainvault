package com.brainvault.utils.mechanics


import com.brainvault.utils.units.Acceleration
import com.brainvault.utils.units.AngularAcceleration
import com.brainvault.utils.units.AngularVelocity
import com.brainvault.utils.units.Force
import com.brainvault.utils.units.Length
import com.brainvault.utils.units.MomentOfInertia
import com.brainvault.utils.units.Nm
import com.brainvault.utils.units.Torque
import kotlin.math.PI

/**
 * Required torque from a moment of inertia and angular acceleration.
 *
 * Formula: **τ = J · α**
 *
 * @param alpha Angular acceleration in rad/s².
 * @return Torque in N·m.
 */
fun MomentOfInertia.requiredTorque(alpha: AngularAcceleration): Torque =
    Torque.ofNm(this.inKgM2 * alpha.inRadPerSec2)

/**
 * Torque required to ramp from standstill to [targetRpm] in [seconds].
 *
 * Uses: α = ω / t, with ω = 2π · RPM / 60.
 *
 * @param targetRpm Target speed in RPM.
 * @param seconds Ramp time in seconds; must be > 0.
 * @return Torque in N·m.
 * @throws IllegalArgumentException if [seconds] ≤ 0.
 */
fun MomentOfInertia.requiredTorqueForRamp(targetRpm: AngularVelocity, seconds: Double): Torque {
    require(seconds > 0.0) { "Ramp time must be > 0" }
    val omega = 2 * PI * targetRpm.inRpm / 60.0
    val alpha = AngularAcceleration(omega / seconds)
    return requiredTorque(alpha)
}

/**
 * Torque required for a linear acceleration through a screw drive.
 *
 * Steps:
 * 1. Convert linear → angular acceleration: α = a · (2π / p)
 * 2. τ = J · α
 *
 * @param a Linear acceleration.
 * @param lead Screw lead (distance per revolution).
 * @return Torque in N·m.
 */
fun MomentOfInertia.requiredTorqueForScrew(a: Acceleration, lead: Length): Torque =
    requiredTorque(a.toAngularForScrew(lead))

/**
 * Torque required for a linear acceleration through a pulley or belt.
 *
 * Steps:
 * 1. Convert linear → angular acceleration: α = a / r
 * 2. τ = J · α
 *
 * @param a Linear acceleration.
 * @param r Radius-carrying type (e.g. [Radius] or [Diameter]).
 * @return Torque in N·m.
 */
fun MomentOfInertia.requiredTorqueForPulley(a: Acceleration, r: HasRadius): Torque =
    requiredTorque(a.toAngularForPulley(r))

fun Force.toTorqueForScrew(lead: Length, efficiency: Double) =
    Torque(this.newton * (lead.inMeters / (2 * PI * efficiency)))

operator fun Torque.times(ratio: GearRatio) = (this.inNm * ratio.ratio).Nm
