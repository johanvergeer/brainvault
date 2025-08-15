package com.brainvault.utils.mechanics

import com.brainvault.utils.units.*
import kotlin.math.PI
import kotlin.time.Duration
import kotlin.time.DurationUnit

/**
 * Converts a linear acceleration to an angular acceleration for a screw drive.
 *
 * Relation:
 *   a = (p / 2π) · α  ⇒  α = a · (2π / p)
 *
 * @param lead Screw lead (travel per revolution) as [com.brainvault.utils.units.Length]; must be > 0.
 * @return Angular acceleration.
 * @throws IllegalArgumentException if [lead] ≤ 0.
 */
fun Acceleration.toAngularForScrew(lead: Length): AngularAcceleration {
    require(lead.inMeters > 0.0) { "Lead must be > 0" }
    val factor = (2 * PI) / lead.inMeters
    return AngularAcceleration(this.inMetersPerSec2 * factor)
}

/**
 * Converts a linear acceleration to an angular acceleration for a belt/pulley.
 *
 * Relation:
 *   a = r · α  ⇒  α = a / r
 *
 * Accepts any [HasRadius], e.g. [Radius] or [Diameter].
 *
 * @param r A dimension from which the radius can be derived; must be > 0.
 * @return Angular acceleration.
 * @throws IllegalArgumentException if the effective radius ≤ 0.
 */
fun Acceleration.toAngularForPulley(r: HasRadius): AngularAcceleration {
    val radius = r.toRadius().meters
    require(radius > 0.0) { "Radius must be > 0" }
    return AngularAcceleration(this.inMetersPerSec2 / radius)
}

/**
 * Angular acceleration for a ramp from standstill to [targetRpm] in [seconds].
 *
 * Relation:
 *   α = ω / t, with ω = 2π · RPM / 60
 *
 * @param targetRpm Target rotational speed.
 * @param seconds Ramp time; must be > 0.
 * @return Required [com.brainvault.utils.units.AngularAcceleration] for the ramp.
 * @throws IllegalArgumentException if [seconds] ≤ 0.
 */
fun requiredAngularAccelerationForRamp(targetRpm: AngularVelocity, seconds: Double): AngularAcceleration =
    AngularAcceleration.forRampToRpm(targetRpm, seconds)

fun Velocity.toAngularForScrew(lead: Length): AngularVelocity =
    AngularVelocity((this.inMillimetersPerMinute / lead.inMillimeters) * ((2 * PI) / 60))

operator fun AngularVelocity.times(ratio: GearRatio) = AngularVelocity(this.inRadPerSec * ratio.ratio)
operator fun AngularVelocity.div(ratio: GearRatio) = AngularVelocity(this.inRadPerSec / ratio.ratio)
operator fun AngularVelocity.div(time: Duration) =
    AngularAcceleration(this.inRadPerSec / time.toDouble(DurationUnit.SECONDS))

operator fun Mass.times(acceleration: Acceleration) = Force(this.inKilograms * acceleration.inMetersPerSec2)
