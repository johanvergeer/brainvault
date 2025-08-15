package com.brainvault.utils.units

import java.util.*

@JvmInline
value class Force(val newton: Double) {
    override fun toString() = "$newton N"
    fun toKnString(decimals: Int = 0): String {

        val factor = this.newton / 1000.0
        val format = "%.${decimals}f kN"
        return String.format(Locale.US, format, factor)
    }

    operator fun plus(other: Force): Force = Force(this.newton + other.newton)
    operator fun minus(other: Force): Force = Force(this.newton - other.newton)
    operator fun times(k: Double): Force = Force(this.newton * k)
    operator fun div(k: Double): Force = Force(this.newton / k)
}

val Double.N get() = Force(this)
val Int.N get() = this.toDouble().N

val Double.kN get() = Force(this * 1_000.0)
val Int.kN get() = this.toDouble().kN
