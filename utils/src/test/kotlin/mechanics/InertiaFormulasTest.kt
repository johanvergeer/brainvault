package com.brainvault.utils.mechanics

import com.brainvault.utils.units.kg
import com.brainvault.utils.units.kgM2
import com.brainvault.utils.units.m
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test
import kotlin.math.PI

class InertiaFormulasTest {

    @Test
    fun `cylinderInertia calculates J = 1_2 m r2`() {
        // Example: m = 2 kg, r = 0.1 m
        // J = 0.5 * 2 * (0.1^2) = 0.01
        val m = 2.0.kg
        val r = 0.1.m.radius
        val J = cylinderInertia(m, r)
        assertEquals(0.01, J.inKgM2, 1e-12)
    }

    @Test
    fun `hollowCylinderInertia calculates J = 1_2 m (ro2 + ri2)`() {
        // m = 1 kg, ro = 0.1 m, ri = 0.05 m
        // J = 0.5 * 1 * (0.1^2 + 0.05^2) = 0.00625
        val m = 1.0.kg
        val J = hollowCylinderInertia(m, 0.1.m.radius, 0.05.m.radius)
        assertEquals(0.00625, J.inKgM2, 1e-12)
    }

    @Test
    fun `linearInertia calculates J = m (p_2pi)2`() {
        // m = 4 kg, lead = 0.01 m
        // J = 4 * (0.01 / (2π))^2
        val m = 4.0.kg
        val lead = 0.01.m.length
        val expected = 4.0 * (0.01 / (2 * PI)) * (0.01 / (2 * PI))
        val J = linearInertia(m, lead)
        assertEquals(expected, J.inKgM2, 1e-12)
    }

    @Test
    fun `MomentOfInertia referredTo applies n2`() {
        val J = 1.0.kgM2
        val n = 2.0.ratio          // input is 2x faster than output
        val result = J.referredTo(n)
        assertEquals(4.0, result.inKgM2, 1e-12)
    }

    @Test
    fun `totalInertia sums and refers all elements`() {
        // J1 = 1 kgm2 at ratio 1 → 1
        // J2 = 2 kgm2 at ratio 0.5 → 2 * 0.5^2 = 0.5
        val total = totalInertia(
            1.0.kgM2 to 1.0.ratio,
            2.0.kgM2 to 0.5.ratio
        )
        assertEquals(1.5, total.inKgM2, 1e-12)
    }

    @Test
    fun `edge-case zero inertia remains zero`() {
        val total = totalInertia(0.0.kgM2 to 2.0.ratio)
        assertEquals(0.0, total.inKgM2, 1e-12)
    }

    @Test
    fun `edge-case ratio equal to zero throws`() {
        // GearRatio constructor should already reject <= 0
        assertThrows(IllegalArgumentException::class.java) {
            GearRatio(0.0)       // this should fail
        }
    }
}
