package com.brainvault.utils.units

/**
 * Torque (SI unit: Newton meter, N·m).
 *
 * Internally stored in **Newton meters**.
 * Use the DSL extensions [.Nm] for readable, error-resistant values in code.
 *
 * Examples:
 * ```
 * val t1 = 5.0.Nm
 * val t2 = 2.Nm
 * val sum = t1 + t2
 * ```
 */
@JvmInline
value class Torque(private val newtonMeter: Double) {

    /** Value in Newton meters (N·m). */
    val inNm: Double get() = newtonMeter

    /** Value in Newton millimeters (N·mm). */
    val inNmm: Double get() = newtonMeter * 1_000.0

    companion object {
        /** Factory from Newton meters (N·m). */
        fun ofNm(v: Double) = Torque(v)
    }

    /** Add two torques. */
    operator fun plus(other: Torque) = Torque(newtonMeter + other.newtonMeter)

    /** Subtract two torques. */
    operator fun minus(other: Torque) = Torque(newtonMeter - other.newtonMeter)

    /** Scale a torque by a scalar. */
    operator fun times(k: Double) = Torque(newtonMeter * k)

    /** Scale down a torque by a scalar. */
    operator fun div(k: Double) = Torque(newtonMeter / k)

    override fun toString() = "%.5f N·m".format(newtonMeter)
}

/** DSL: Newton meters (N·m). */
val Double.Nm: Torque get() = Torque.ofNm(this)
val Int.Nm: Torque get() = this.toDouble().Nm