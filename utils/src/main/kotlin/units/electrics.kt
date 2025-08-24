package com.brainvault.utils.units
import kotlin.math.sqrt


/**
 * Represents electric current stored internally as RMS amperes (Arms).
 *
 * Domain notes:
 * - **Arms** = effective current of an AC waveform (default for AC drives/servos).
 * - **Apeak** = instantaneous peak of a sine: Apeak = √2 * Arms.
 * - If vendor docs say just "**A**" for AC servo "rated/continuous current", read it as **Arms**.
 * - "**Peak current**" may be given as Apeak or Arms; check the suffix. If unclear but labeled “peak”,
 *   assume **Apeak** for sine drives and convert to RMS.
 * - DC supply/bus current (often just "A" on PSU) is **not** the same as phase current; don’t map it here.
 *
 * Common vendor labels → this model:
 * - Rated/Continuous/Nominal current → Arms
 * - Max/Peak current (Apeak)        → use [peak(...)] (converted to RMS)
 * - Max/Peak current (Arms)         → use [rms(...)]
 * - Line/Phase current (servo)      → usually Arms
 */
@JvmInline
value class Ampere private constructor(private val rms: Double) {
    override fun toString() = "$rms Arms"
    val inArms get() = rms
    val inMilliArms get() = rms * 1_000.0
    fun toPeak(): Double = rms * sqrt(2.0) // returns Apeak as a Double
    companion object {
        /** Use when the datasheet gives RMS (Arms). */
        fun rms(valueArms: Double) = Ampere(valueArms)
        /** Use when the datasheet gives PEAK (Apeak); converts to internal RMS. */
        fun peak(valueApeak: Double) = Ampere(valueApeak / sqrt(2.0))
    }
}

// Convenience creators (intentionally no ambiguous `.A`)
val Double.arms get() = Ampere.rms(this)
val Int.arms get() = Ampere.rms(this.toDouble())
val Double.aPeak get() = Ampere.peak(this)
val Int.aPeak get() = Ampere.peak(this.toDouble())

@JvmInline
value class Power(private val watt: Double) {
    override fun toString() = "$watt W"

    val inWatt get() = watt
    val inKiloWatt get() = watt * 1_000.0
}

val Double.W get() = Power(this)
val Int.W get() = this.toDouble().W
val Double.kW get() = Power(this * 1_000.0)
val Int.kW get() = this.toDouble().kW

@JvmInline
value class Voltage(private val volts: Double) {
    override fun toString() = "$volts V"
}

val Double.V get() = Voltage(this)
val Int.V get() = this.toDouble().V
