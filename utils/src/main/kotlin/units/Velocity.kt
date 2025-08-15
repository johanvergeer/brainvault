package com.brainvault.utils.units

@JvmInline
value class Velocity(private val metersPerSecond: Double) {

    val inMetersPerSec: Double get() = metersPerSecond
    val inMillimetersPerMinute: Double get() = metersPerSecond * 1_000.0 * 60.0

    companion object {
        fun ofMetersPerSecond(v: Double) = Velocity(v)
        fun ofMillimetersPerMinute(v: Double) = Velocity(v / 1_000.0 / 60.0)
    }

    override fun toString(): String {
        return "%.5f m/s".format(inMetersPerSec)
    }
}

val Double.mps get() = Velocity.ofMetersPerSecond(this)
val Int.mps get() = this.toDouble().mps

val Double.mmPerMinute get() = Velocity.ofMillimetersPerMinute(this)
val Int.mmPerMinute get() = this.toDouble().mmPerMinute

@JvmInline
value class AngularVelocity(private val radiansPerSecond: Double) {
    val inRadPerSec: Double get() = radiansPerSecond
    val inDegreePerSec: Double get() = radiansPerSecond * 180.0 / Math.PI
    val inRpm: Double get() = radiansPerSecond * 60.0 / (2 * Math.PI)

    companion object {
        fun ofRadPerSec(v: Double) = AngularVelocity(v)
        fun ofDegreePerSec(v: Double) = AngularVelocity(v * Math.PI / 180.0)
        fun ofRpm(v: Double) = AngularVelocity(v * (2 * Math.PI) / 60.0)
    }

    override fun toString(): String {
        return "%.1f rpm".format(inRpm)
    }
}

val Double.radPerSec get() = AngularVelocity.ofRadPerSec(this)
val Int.radPerSec get() = this.toDouble().radPerSec

val Double.degreePerSec get() = AngularVelocity.ofDegreePerSec(this)
val Int.degreePerSec get() = this.toDouble().degreePerSec

val Double.rpm get() = AngularVelocity.ofRpm(this)
val Int.rpm get() = this.toDouble().rpm
