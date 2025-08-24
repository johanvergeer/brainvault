package com.brainvault.utils.hardware.belts

import com.brainvault.utils.units.mm
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class BeltsTest {

    @Test
    fun `creates consistent length when constructed from teeth count`() {
        val belt = TimingBelt.ofTeethCount(8.0.mm.length, 30.0.mm.length, 90)
        assertEquals(8.0 * 90, belt.length.inMillimeters, 1e-9)
        assertEquals(90, belt.teethCount)
    }

    @Test
    fun `creates correct belt when length is exact multiple of pitch`() {
        val belt = TimingBelt.ofLength(8.0.mm.length, 30.0.mm.length, 720.0.mm.length)
        assertEquals(720.0, belt.length.inMillimeters, 1e-9)
        assertEquals(90, belt.teethCount)
    }

    @Test
    fun `throws when length is not an exact multiple of pitch`() {
        assertFailsWith<IllegalArgumentException> {
            TimingBelt.ofLength(8.0.mm.length, 30.0.mm.length, 721.0.mm.length)
        }
    }

    @Test
    fun `throws when constructed with non positive tooth count`() {
        assertFailsWith<IllegalArgumentException> {
            TimingBelt.ofTeethCount(8.0.mm.length, 30.0.mm.length, 0)
        }
    }

    @Test
    fun `throws when pitch or length are non positive`() {
        assertFailsWith<IllegalArgumentException> {
            TimingBelt.ofLength(0.0.mm.length, 30.0.mm.length, 720.0.mm.length)
        }
        assertFailsWith<IllegalArgumentException> {
            TimingBelt.ofLength(8.0.mm.length, 30.0.mm.length, 0.0.mm.length)
        }
    }
}