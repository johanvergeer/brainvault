package com.brainvault.utils.units

/**
 * Mass (SI base unit: kilogram, kg).
 *
 * Internally stored in **kilograms**.
 * Use the DSL extensions [.kg] and [.g] for readable, error-resistant values in code.
 *
 * Examples:
 * ```
 * val m1 = 5.kg
 * val m2 = 250.g
 * val total = m1 + m2
 * ```
 */
@JvmInline
value class Mass(private val kilograms: Double) {

    /** Value in kilograms (kg). */
    val inKilograms: Double get() = kilograms

    /** Value in grams (g). */
    val inGrams: Double get() = kilograms * 1_000.0

    companion object {
        /** Factory from kilograms (kg). */
        fun ofKilograms(v: Double) = Mass(v)

        /** Factory from grams (g). */
        fun ofGrams(v: Double) = Mass(v / 1_000.0)
    }

    /** Add two masses. */
    operator fun plus(other: Mass) = Mass(kilograms + other.kilograms)

    /** Subtract two masses. */
    operator fun minus(other: Mass) = Mass(kilograms - other.kilograms)

    /** Scale a mass by a scalar. */
    operator fun times(k: Double) = Mass(kilograms * k)

    /** Scale down a mass by a scalar. */
    operator fun div(k: Double) = Mass(kilograms / k)

    override fun toString() = "$kilograms kg"
}

/** DSL: kilograms (kg). */
val Double.kg: Mass get() = Mass.ofKilograms(this)
/** DSL: kilograms (kg). */
val Int.kg: Mass get() = Mass.ofKilograms(this.toDouble())

/** DSL: grams (g). */
val Double.g: Mass get() = Mass.ofGrams(this)
/** DSL: grams (g). */
val Int.g: Mass get() = Mass.ofGrams(this.toDouble())