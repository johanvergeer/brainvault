package com.brainvault.utils.hardware.belts

import com.brainvault.utils.units.Length
import com.brainvault.utils.units.mm
import kotlin.math.asin
import kotlin.math.sqrt

/**
 * Computes the theoretical pitch length of a timing belt given two pulleys and their center distance.
 *
 * The belt length is calculated as:
 *
 * \[
 * L = \frac{\pi}{2}(D_L + D_S) +
 *     (D_L - D_S)\,\arcsin\!\left(\frac{D_L - D_S}{2C}\right) +
 *     2 \sqrt{\,C^2 - \frac{1}{4}(D_L - D_S)^2\,}
 * \]
 *
 * where:
 * - \(L\) = pitch length of the belt [mm]
 * - \(D_L\) = pitch diameter of the larger pulley [mm]
 * - \(D_S\) = pitch diameter of the smaller pulley [mm]
 * - \(C\) = center distance between pulley axes [mm]
 *
 * This formula accounts for both the arc of contact on each pulley and the straight belt spans.
 *
 * @param centerDistance Center distance between pulley axes
 * @param pulley1 One pulley of the belt drive
 * @param pulley2 The other pulley of the belt drive
 * @return Theoretical belt pitch length as a [Length]
 *
 * @usesMathJax
 */
fun timingBeltLength(centerDistance: Length, pulley1: TimingBeltPulley, pulley2: TimingBeltPulley): Length {
    val Cmm = centerDistance.inMillimeters
    require(Cmm > 0.0) { "Center distance must be > 0" }

    // Ensure Ds = smaller pitch diameter, Dl = larger pitch diameter
    val Ds = minOf(pulley1.pitchDiameter.millimeters, pulley2.pitchDiameter.millimeters)
    val Dl = maxOf(pulley1.pitchDiameter.millimeters, pulley2.pitchDiameter.millimeters)
    require(Ds > 0.0 && Dl > 0.0) { "Pitch diameters must be > 0" }

    val d = Dl - Ds
    val half = 0.5
    require(Cmm >= d * half) { "Center distance must satisfy C ≥ (Dl - Ds)/2" }

    // Clamp asin argument for numerical stability at boundary cases
    val x = (d / (2.0 * Cmm)).coerceIn(-1.0, 1.0)

    // Terms of the belt length equation
    val arcTerm   = (Dl + Ds) * Math.PI / 2.0
    val wedgeTerm = d * asin(x)
    val spanTerm  = 2.0 * sqrt(Cmm * Cmm - (half * d) * (half * d))

    return (arcTerm + wedgeTerm + spanTerm).mm.length
}
