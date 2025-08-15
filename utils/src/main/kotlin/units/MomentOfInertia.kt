package com.brainvault.utils.units

/**
 * Represents a moment of inertia (J).
 *
 * Use this type to perform calculations with moments of inertia without
 * having to remember or manually convert units. All values are handled
 * internally in SI units, ensuring that additions, subtractions, and
 * comparisons are always consistent.
 *
 * You can create a value from kilogram–square meters ([ofKgM2]) or
 * gram–square millimeters ([ofGMm2]), and retrieve it in either unit
 * via [inKgM2] or [inGMm2].
 *
 * Supports addition and subtraction via the `+` and `-` operators.
 */
@JvmInline
value class MomentOfInertia(private val kgM2: Double) {
    val inKgM2: Double get() = kgM2
    val inGMm2: Double get() = kgM2 / 1e-9   // 1 g·mm² = 1e-9 kg·m²

    companion object {
        fun ofKgM2(v: Double) = MomentOfInertia(v)
        fun ofGMm2(v: Double) = MomentOfInertia(v * 1e-9)
    }

    override fun toString(): String {
        return "%.5e kg⋅m²".format(kgM2)
    }

    operator fun plus(other: MomentOfInertia) = MomentOfInertia(kgM2 + other.kgM2)
    operator fun minus(other: MomentOfInertia) = MomentOfInertia(kgM2 - other.kgM2)
    operator fun div(k: Double) = MomentOfInertia(kgM2 / k)
    operator fun div(k: Int) = MomentOfInertia(kgM2 / k)
}

/**
 * Creates a [MomentOfInertia] from a value in kilogram–square meters (kg·m²).
 *
 * @receiver Moment of inertia in kg·m².
 */
val Double.kgM2: MomentOfInertia get() = MomentOfInertia.ofKgM2(this)

/**
 * Creates a [MomentOfInertia] from a value in kilogram–square meters (kg·m²).
 *
 * @receiver Moment of inertia in kg·m².
 */
val Int.kgM2: MomentOfInertia get() = (this.toDouble()).kgM2

/**
 * Creates a [MomentOfInertia] from a value in gram–square millimeter (g·mm²).
 *
 * @receiver Moment of inertia in g·mm².
 */
val Double.gMm2: MomentOfInertia get() = MomentOfInertia.ofGMm2(this)

/**
 * Creates a [MomentOfInertia] from a value in gram–square millimeter (g·mm²).
 *
 * @receiver Moment of inertia in g·mm².
 */
val Int.gMm2: MomentOfInertia get() = (this.toDouble()).gMm2