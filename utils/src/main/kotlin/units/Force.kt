package com.brainvault.utils.units

import java.util.*
import kotlin.math.pow

@JvmInline
value class Force(val newton: Double) {
    override fun toString() = "%.2f N".format(newton)
    fun toKnString(decimals: Int = 0): String {

        val factor = this.newton / 1000.0
        val format = "%.${decimals}f kN"
        return String.format(Locale.US, format, factor)
    }

    operator fun plus(other: Force): Force = Force(this.newton + other.newton)
    operator fun minus(other: Force): Force = Force(this.newton - other.newton)
    operator fun times(k: Double): Force = Force(this.newton * k)
    operator fun times(k: Int): Force = Force(this.newton * k)
    operator fun times(k: Force): Force = Force(this.newton * k.newton)
    operator fun div(k: Double): Force = Force(this.newton / k)
    operator fun compareTo(other: Force): Int = this.newton.compareTo(other.newton)

    /**
     * Raises this value to the integer power [n].
     *
     * @param n
     */
    fun pow(n: Int) = Force(this.newton.pow(n))

}

val Double.N get() = Force(this)
val Int.N get() = this.toDouble().N

operator fun Int.times(force: Force) = force * this

val Double.kN get() = Force(this * 1_000.0)
val Int.kN get() = this.toDouble().kN
