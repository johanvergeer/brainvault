package com.brainvault.utils.units

import kotlin.math.PI

/**
 * Linear acceleration.
 *
 * Unit: meters per second squared (m/s²).
 *
 * Use this type to express and compute linear motion requirements in a
 * consistent SI system.
 */
@JvmInline
value class Acceleration(private val mps2: Double) {

    /** Value in m/s². */
    val inMetersPerSec2: Double get() = mps2

    /** Value in mm/s². */
    val inMillimetersPerSec2: Double get() = mps2 * 1000.0

    companion object {
        /** Creates an [Acceleration] from m/s². */
        fun ofMetersPerSec2(v: Double) = Acceleration(v)

        /** Creates an [Acceleration] from mm/s². */
        fun ofMillimetersPerSec2(v: Double) = Acceleration(v / 1000.0)
    }

    /** Adds two linear accelerations. */
    operator fun plus(other: Acceleration) = Acceleration(mps2 + other.mps2)

    /** Subtracts a linear acceleration. */
    operator fun minus(other: Acceleration) = Acceleration(mps2 - other.mps2)

    /** Scales this acceleration by a scalar. */
    operator fun times(k: Double) = Acceleration(mps2 * k)

    /** Divides this acceleration by a scalar. */
    operator fun div(k: Double) = Acceleration(mps2 / k)

    override fun toString() = "$mps2 m/s²"
}

/** DSL: meters per second squared (m/s²). */
val Double.mps2: Acceleration get() = Acceleration.ofMetersPerSec2(this)
val Int.mps2: Acceleration get() = this.toDouble().mps2

/** DSL: millimeters per second squared (mm/s²). */
val Double.mmps2: Acceleration get() = Acceleration.ofMillimetersPerSec2(this)
val Int.mmps2: Acceleration get() = this.toDouble().mmps2

/**
 * Angular acceleration.
 *
 * Unit: radians per second squared (rad/s²).
 *
 * Use this type for rotational motion requirements (e.g., motor ramps).
 */
@JvmInline
value class AngularAcceleration(private val radPerSec2: Double) {

    /** Value in rad/s². */
    val inRadPerSec2: Double get() = radPerSec2

    /**
     * Value in revolutions per minute per second (RPM/s).
     *
     * Helpful for motor specifications.
     * Conversion: 1 RPM/s = (2π / 60) rad/s².
     */
    val inRpmPerSec: Double get() = radPerSec2 * 60.0 / (2 * PI)

    companion object {
        /** Creates an [AngularAcceleration] from rad/s². */
        fun ofRadPerSec2(v: Double) = AngularAcceleration(v)

        /**
         * Creates an [AngularAcceleration] from a change in RPM over time.
         *
         * Relation:
         *   α = Δω / t, with ω = 2π · RPM / 60
         *
         * @param deltaRpm Change in RPM.
         * @param seconds Time interval; must be > 0.
         * @throws IllegalArgumentException if [seconds] ≤ 0.
         */
        fun ofDeltaRpmOverTime(deltaRpm: AngularVelocity, seconds: Double): AngularAcceleration {
            require(seconds > 0.0) { "seconds must be > 0" }
            val deltaOmega = 2 * PI * deltaRpm.inRpm / 60.0
            return AngularAcceleration(deltaOmega / seconds)
        }

        /**
         * Creates an [AngularAcceleration] for a ramp from 0 to [targetRpm] in [seconds].
         *
         * Relation:
         *   α = ω / t, with ω = 2π · RPM / 60
         *
         * @param targetRpm Target rotational speed.
         * @param seconds Ramp time; must be > 0.
         * @throws IllegalArgumentException if [seconds] ≤ 0.
         */
        fun forRampToRpm(targetRpm: AngularVelocity, seconds: Double): AngularAcceleration =
            ofDeltaRpmOverTime(deltaRpm = targetRpm, seconds = seconds)
    }

    /** Adds two angular accelerations. */
    operator fun plus(other: AngularAcceleration) = AngularAcceleration(radPerSec2 + other.radPerSec2)

    /** Subtracts an angular acceleration. */
    operator fun minus(other: AngularAcceleration) = AngularAcceleration(radPerSec2 - other.radPerSec2)

    /** Scales this angular acceleration by a scalar. */
    operator fun times(k: Double) = AngularAcceleration(radPerSec2 * k)

    /** Divides this angular acceleration by a scalar. */
    operator fun div(k: Double) = AngularAcceleration(radPerSec2 / k)

    override fun toString() = "$radPerSec2 rad/s²"
}

/** DSL: radians per second squared (rad/s²). */
val Double.radPerSec2: AngularAcceleration get() = AngularAcceleration.ofRadPerSec2(this)
val Int.radPerSec2: AngularAcceleration get() = this.toDouble().radPerSec2