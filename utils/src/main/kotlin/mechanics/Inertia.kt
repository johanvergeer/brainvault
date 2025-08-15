package com.brainvault.utils.mechanics


import com.brainvault.utils.units.AngularAcceleration
import com.brainvault.utils.units.Length
import com.brainvault.utils.units.Mass
import com.brainvault.utils.units.MomentOfInertia
import com.brainvault.utils.units.Torque
import kotlin.math.PI
import kotlin.math.pow


/**
 * Calculates the mass moment of inertia (J) of a solid cylinder
 * about its central axis.
 *
 * Formula: J = 1/2 · m · r²
 *
 * @param m Mass of the cylinder.
 * @param r A dimension from which the radius can be derived
 *   (e.g., a [Radius] or [Diameter]).
 * @return The calculated mass moment of inertia as a [MomentOfInertia].
 */
fun cylinderInertia(m: Mass, r: HasRadius) =
    MomentOfInertia(0.5 * m.inKilograms * r.toRadius().meters.pow(2))


/**
 * Calculates the mass moment of inertia (J) of a hollow cylinder about its central axis.
 *
 * Formula: J = 1/2 · m · (ro² + ri²)
 *
 * @param m Mass of the hollow cylinder.
 * @param ro Outer dimension from which the radius can be derived
 * (e.g., a [Radius] or [Diameter]).
 * @param ri Inner dimension from which the radius can be derived
 * (e.g., a [Radius] or [Diameter]).
 * @return The calculated mass moment of inertia as a [MomentOfInertia].
 */
fun hollowCylinderInertia(m: Mass, ro: HasRadius, ri: HasRadius) =
    MomentOfInertia(
        0.5 * m.inKilograms * (ro.toRadius().meters.pow(2) + ri.toRadius().meters.pow(2))
    )


/**
 * Calculates the equivalent rotational moment of inertia of a translating mass
 * driven by a screw with a given lead.
 *
 * Formula: J_lin = m * (p / (2π))²
 *
 * @param m Translating mass
 * @param lead Screw lead (distance traveled per revolution)
 * @return Equivalent moment of inertia in kg·m²
 */
fun linearInertia(m: Mass, lead: Length): MomentOfInertia {
    val term = lead.inMeters / (2 * PI)
    return MomentOfInertia(m.inKilograms * term * term)
}


/**
 * Refers this moment of inertia through a gear ratio to calculate the equivalent
 * moment of inertia at the input shaft.
 *
 * Formula: J_in = J_out * n²
 * where n is the gear ratio (input speed / output speed)
 *
 * @param ratio The gear ratio to refer through
 * @return Equivalent moment of inertia at the input shaft
 */
fun MomentOfInertia.referredTo(ratio: GearRatio) =
    MomentOfInertia(this.inKgM2 * ratio.ratio.pow(2))


/**
 * Calculates the total equivalent moment of inertia at the input shaft,
 * given a set of inertia terms and their corresponding gear ratios.
 *
 * Each inertia term is referred to the input shaft using the gear ratio,
 * and all terms are summed.
 *
 * @param terms Vararg of Pairs, each containing a moment of inertia and its gear ratio
 * @return Total equivalent moment of inertia at the input shaft
 *
 * Example usage:
 * ```
 * val total = totalInertia(
 *     motorInertia to 1.0.ratio,      // Direct coupled
 *     loadInertia to 0.25.ratio       // Through 4:1 reduction
 * )
 * ```
 */
fun totalInertia(vararg terms: Pair<MomentOfInertia, GearRatio>) =
    terms.fold(MomentOfInertia(0.0)) { acc, (J, r) ->
        acc + J.referredTo(r)
    }

operator fun MomentOfInertia.times(alpha: AngularAcceleration) = Torque(this.inKgM2 * alpha.inRadPerSec2)
