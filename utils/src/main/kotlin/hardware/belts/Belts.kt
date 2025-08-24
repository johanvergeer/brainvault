package com.brainvault.utils.hardware.belts

import com.brainvault.utils.units.Length

/**
 * Represents a timing belt with a given pitch, width, total length, and number of teeth.
 *
 * The length and tooth count are always consistent:
 * \[
 * L = z \cdot p
 * \]
 * where:
 * - \(L\) = total belt length [mm]
 * - \(z\) = number of teeth
 * - \(p\) = tooth pitch [mm]
 *
 * Instances are created via the factory methods in the companion object.
 *
 * @usesMathJax
 */
@ConsistentCopyVisibility
data class TimingBelt private constructor(
    val pitch: Length,
    val width: Length,
    val length: Length,
    val teethCount: Int
) {
    companion object {
        /**
         * Creates a [TimingBelt] from a specified tooth count.
         *
         * \[
         * L = z \cdot p
         * \]
         *
         * @param pitch Tooth pitch as a [Length]
         * @param width Belt width as a [Length]
         * @param teethCount Number of teeth (must be > 0)
         * @return A new [TimingBelt] instance
         * @throws IllegalArgumentException if [teethCount] ≤ 0
         *
         * @usesMathJax
         */
        fun ofTeethCount(pitch: Length, width: Length, teethCount: Int): TimingBelt {
            require(teethCount > 0) { "teethCount must be > 0" }
            return TimingBelt(pitch, width, pitch * teethCount, teethCount)
        }

        /**
         * Creates a [TimingBelt] from a specified total length.
         *
         * Valid only if the given length is an exact multiple of the pitch:
         * \[
         * z = \frac{L}{p}
         * \]
         *
         * @param pitch Tooth pitch as a [Length]
         * @param width Belt width as a [Length]
         * @param length Total belt length as a [Length]
         * @return A new [TimingBelt] instance
         * @throws IllegalArgumentException if [pitch] or [length] ≤ 0
         * @throws IllegalArgumentException if [length] is not an exact multiple of [pitch]
         *
         * @usesMathJax
         */
        fun ofLength(pitch: Length, width: Length, length: Length): TimingBelt {
            require(pitch.inMillimeters > 0.0) { "pitch must be > 0" }
            require(length.inMillimeters > 0.0) { "length must be > 0" }

            val teeth = length.inMillimeters / pitch.inMillimeters
            require(teeth % 1.0 < 1e-6) { "length must be an exact multiple of pitch" }

            return TimingBelt(pitch, width, length, teeth.toInt())
        }
    }
}
