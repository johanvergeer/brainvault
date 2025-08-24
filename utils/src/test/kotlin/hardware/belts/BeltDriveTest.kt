package com.brainvault.utils.hardware.belts

import com.brainvault.utils.units.mm
import kotlin.math.PI
import kotlin.test.Test
import kotlin.test.assertEquals

class BeltDriveTest {
    @Test
    fun `computes correct length for equal pulleys`() {
        val timingBeltPulley = TimingBeltPulley(8.mm.length, 18, 30.mm.length)
        val C = 100.0.mm.length
        val D = timingBeltPulley.pitchDiameter
        val L = timingBeltLength(
            C,
            timingBeltPulley,
            timingBeltPulley.copy()
        )

        val expected = (PI * D.millimeters + 2 * C.inMillimeters).mm.length
        assertEquals(expected.inMillimeters, L.inMillimeters, 1e-6)
    }

    @Test
    fun `computes length close to nominal 720mm for Maho example`() {
        val C = 165.5.mm.length
        val Ds = TimingBeltPulley(8.mm.length, 18, 30.mm.length)
        val Dl = TimingBeltPulley(8.mm.length, 72, 30.mm.length)
        val L = timingBeltLength(C, Ds, Dl)

        assertEquals(720.0, L.inMillimeters, 0.5)
    }

    @Test
    fun `returns same length when swapping small and large pulleys`() {
        val C = 150.0.mm.length
        val Ds = TimingBeltPulley(8.mm.length, 20, 30.mm.length)
        val Dl = TimingBeltPulley(8.mm.length, 72, 30.mm.length)

        val L1 = timingBeltLength(C, Ds, Dl)
        val L2 = timingBeltLength(C, Dl, Ds)

        assertEquals(L1.inMillimeters, L2.inMillimeters, 1e-9)
    }
}