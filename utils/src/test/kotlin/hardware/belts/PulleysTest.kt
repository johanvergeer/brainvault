package com.brainvault.utils.hardware.belts

import com.brainvault.utils.units.mm
import kotlin.math.PI
import kotlin.test.Test
import kotlin.test.assertEquals

class PulleysTest {

    @Test
    fun `computes pitch diameter for simple case`() {
        val p = 8.0.mm.length
        val z = 18

        val pulley = TimingBeltPulley(p, z, 30.mm.length)

        val expected = (z * p.inMillimeters / PI)
        assertEquals(expected, pulley.pitchDiameter.millimeters, 1e-6)
    }

    @Test
    fun `computes pitch diameter correctly for symmetry example`() {
        // A pulley with z=π teeth and p=1 mm has pitch diameter = 1 mm
        val p = 1.0.mm.length
        val z = PI.toInt() // ~3 teeth
        val pulley = TimingBeltPulley(p, z, 30.mm.length)

        val expected = z * p.inMillimeters / PI
        assertEquals(expected, pulley.pitchDiameter.millimeters, 1e-6)
    }

    @Test
    fun `computes pitch diameter for known HTD-8 values`() {
        val d1 = TimingBeltPulley(8.mm.length, 18, 30.mm.length).pitchDiameter
        val d2 = TimingBeltPulley(8.mm.length, 72, 30.mm.length).pitchDiameter

        assertEquals(45.84, d1.millimeters, 0.01) // HTD-8, 18T
        assertEquals(183.35, d2.millimeters, 0.01) // HTD-8, 72T
    }

    @Test
    fun `scales pitch diameter linearly with pitch`() {
        val d1 = TimingBeltPulley(5.mm.length, 20, 30.mm.length).pitchDiameter
        val d2 = TimingBeltPulley(10.mm.length, 20, 30.mm.length).pitchDiameter

        // Doubling the pitch doubles the diameter for same number of teeth
        assertEquals(d1.millimeters * 2, d2.millimeters, 1e-6)
    }

    @Test
    fun `throws when created with non positive parameters`() {
        // teethCount = 0 should fail
        kotlin.test.assertFailsWith<IllegalArgumentException> {
            TimingBeltPulley(8.mm.length, 0, 30.mm.length)
        }
        // pitch <= 0 should fail
        kotlin.test.assertFailsWith<IllegalArgumentException> {
            TimingBeltPulley(0.0.mm.length, 18, 30.mm.length)
        }
        // width <= 0 should fail
        kotlin.test.assertFailsWith<IllegalArgumentException> {
            TimingBeltPulley(8.0.mm.length, 18, 0.0.mm.length)
        }
    }
}