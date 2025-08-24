package com.brainvault.utils.hardware.belts

import com.brainvault.utils.mechanics.diameter
import com.brainvault.utils.units.Length
import com.brainvault.utils.units.mm
import kotlin.math.PI

/**
 * Represents a timing belt pulley defined by its pitch, number of teeth, and width.
 *
 * The pitch diameter of a pulley is derived from the number of teeth `z` and the belt pitch `p`:
 *
 * \[
 * d_p = \frac{z \cdot p}{\pi}
 * \]
 *
 * where:
 * - \(d_p\) = pitch diameter [mm]
 * - \(z\) = number of pulley teeth
 * - \(p\) = belt pitch [mm]
 *
 * The pitch radius is then simply \(r_p = d_p / 2\).
 *
 * @property pitch Tooth pitch of the belt as a [Length]
 * @property teethCount Number of teeth on the pulley (must be > 0)
 * @property width Pulley width as a [Length]
 *
 * @usesMathJax
 */
data class TimingBeltPulley(
    val pitch: Length,
    val teethCount: Int,
    val width: Length
) {
    init {
        require(teethCount > 0) { "teethCount must be > 0" }
        require(pitch.inMillimeters > 0.0) { "pitch must be > 0" }
        require(width.inMillimeters > 0.0) { "width must be > 0" }
    }

    /**
     * Computes the pitch diameter of this [TimingBeltPulley].
     */
    val pitchDiameter =
        ((this.teethCount * this.pitch.inMillimeters) / PI).mm.diameter
}