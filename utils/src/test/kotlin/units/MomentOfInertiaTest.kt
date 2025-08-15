package com.brainvault.utils.units

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class MomentOfInertiaTest {

    @Test
    fun `ofKgM2 stores the value in kgm2`() {
        val J = MomentOfInertia.ofKgM2(0.25)
        assertEquals(0.25, J.inKgM2)
    }

    @Test
    fun `ofGMm2 converts gmm2 to kgm2`() {
        // 1 g·mm² = 1e-9 kg·m²  →  2e9 g·mm² = 2.0 kg·m²
        val J = MomentOfInertia.ofGMm2(2_000_000_000.0)
        assertEquals(2.0, J.inKgM2)
    }

    @Test
    fun `inGMm2 converts kgm2 to gmm2`() {
        // 1 kg·m² = 1e9 g·mm²
        val J = MomentOfInertia.ofKgM2(0.5)
        assertEquals(500_000_000.0, J.inGMm2, 1e-6)
    }

    @Test
    fun `plus adds two inertias`() {
        val a = MomentOfInertia.ofKgM2(0.2)
        val b = MomentOfInertia.ofKgM2(0.3)
        val result = a + b
        assertEquals(0.5, result.inKgM2)
    }

    @Test
    fun `minus subtracts two inertias`() {
        val a = MomentOfInertia.ofKgM2(1.0)
        val b = MomentOfInertia.ofKgM2(0.25)
        val result = a - b
        assertEquals(0.75, result.inKgM2)
    }

    @Test
    fun `dsl kgM2 creates a MomentOfInertia from Double`() {
        val inertia = 0.5.kgM2
        assertEquals(0.5, inertia.inKgM2)
    }

    @Test
    fun `dsl kgM2 creates a MomentOfInertia from Int`() {
        val inertia = 1.kgM2
        assertEquals(1.0, inertia.inKgM2)
    }

    @Test
    fun `dsl gMm2 creates a MomentOfInertia from Double`() {
        // 2e9 g·mm² = 2.0 kg·m²
        val inertia = 2_000_000_000.0.gMm2
        assertEquals(2.0, inertia.inKgM2, 1e-6)
    }

    @Test
    fun `dsl gMm2 creates a MomentOfInertia from Int`() {
        // 1e9 g·mm² = 1.0 kg·m²
        val inertia = 1_000_000_000.gMm2
        assertEquals(1.0, inertia.inKgM2, 1e-6)
    }
}