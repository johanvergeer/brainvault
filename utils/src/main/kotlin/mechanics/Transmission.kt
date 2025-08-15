package com.brainvault.utils.mechanics

/**
 * Represents a gear ratio, defined as the ratio of input speed to output speed.
 *
 * A gear ratio greater than 1.0 indicates a speed reduction (and torque multiplication),
 * while a gear ratio less than 1.0 indicates a speed increase (and torque reduction).
 *
 * Examples:
 * - `4.0.ratio` → input spins 4× faster than output (speed reduction, torque increase)
 * - `0.25.ratio` → output spins 4× faster than input (speed increase, torque decrease)
 *
 * @property ratio The gear ratio, expressed as `input_speed / output_speed`.
 * @throws IllegalArgumentException if [ratio] is less than or equal to 0.
 */
@JvmInline
value class GearRatio(val ratio: Double) {
    init { require(ratio > 0) { "Gear ratio must be positive" } }

    override fun toString() = "$ratio"
}

/**
 * Creates a [GearRatio] from a [Double] value.
 *
 * Example:
 * ```
 * val gr = 4.0.ratio  // input is 4× faster than output
 * ```
 */
val Double.ratio: GearRatio get() = GearRatio(this)

/**
 * Creates a [GearRatio] from an [Int] value.
 *
 * Example:
 * ```
 * val gr = 4.ratio  // input is 4× faster than output
 * ```
 */
val Int.ratio: GearRatio get() = this.toDouble().ratio
