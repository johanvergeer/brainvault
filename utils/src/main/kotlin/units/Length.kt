package com.brainvault.utils.units

/**
 * Linear length.
 *
 * Unit: meters (m).
 *
 * Use this type to express and compute distances consistently in SI.
 */
@JvmInline
value class Length(private val meters: Double) {

    /** Value in meters (m). */
    val inMeters: Double get() = meters

    /** Value in millimeters (mm). */
    val inMillimeters: Double get() = meters * 1_000.0

    /** Adds two lengths. */
    operator fun plus(other: Length) = Length(meters + other.meters)

    /** Subtracts a length. */
    operator fun minus(other: Length) = Length(meters - other.meters)

    /** Scales this length by a scalar. */
    operator fun times(k: Double) = Length(meters * k)
    operator fun times(k: Int) = Length(meters * k)

    /** Divides this length by a scalar. */
    operator fun div(k: Double) = Length(meters / k)

    override fun toString() = "$meters m"

    companion object {
        /** Creates a [Length] from meters. */
        fun ofMeters(v: Double) = Length(v)

        /** Creates a [Length] from millimeters. */
        fun ofMillimeters(v: Double) = Length(v / 1_000.0)
    }
}

/**
 * Explicit meter value.
 *
 * Unit: meters (m).
 *
 * Example:
 * ```
 * val L = 0.5.m.length   // 0.5 m as Length
 * ```
 */
@JvmInline
value class Meter(val value: Double) {
    /** Converts to [Length]. */
    fun toLength() = Length.ofMeters(value)
    override fun toString() = "$value m"
    val length get() = toLength()
}

/**
 * Explicit millimeter value.
 *
 * Unit: millimeters (mm).
 *
 * Example:
 * ```
 * val L = 16.mm.length   // 16 mm as Length
 * ```
 */
@JvmInline
value class Millimeter(val value: Double) {
    /** Converts to [Length]. */
    fun toLength() = Length.ofMillimeters(value)
    override fun toString() = "$value mm"

    val length get() = toLength()
}

/** DSL: literals for meters and millimeters. */
val Double.m: Meter get() = Meter(this)
val Double.mm: Millimeter get() = Millimeter(this)
val Int.m: Meter get() = Meter(this.toDouble())
val Int.mm: Millimeter get() = Millimeter(this.toDouble())