package com.brainvault.utils.mechanics

import com.brainvault.utils.units.*
import kotlin.math.PI

/**
 * Contract for types that can provide an effective radius.
 *
 * Implemented by [Radius] and [Diameter].
 */
interface HasRadius {
    /** Returns the equivalent [Radius]. */
    fun toRadius(): Radius
}

/**
 * Geometric diameter.
 *
 * Base dimension: [Length].
 *
 * Provides accessors for [circumference] and [area] (circle with this diameter).
 */
@JvmInline
value class Diameter(private val length: Length) : HasRadius {

    /** Diameter in meters (m). */
    val meters get() = length.inMeters

    /** Diameter in millimeters (mm). */
    val millimeters get() = length.inMillimeters

    /** Converts to the corresponding [Radius]. */
    override fun toRadius() = Radius(length / 2.0)

    /** Circle circumference for this diameter: `C = π·d`. */
    fun circumference() = PI * meters

    /** Circle area for this diameter: `A = π·(d/2)²`. */
    fun area() = PI * (meters / 2.0) * (meters / 2.0)

    /** Adds two diameters (component-wise). */
    operator fun plus(other: Diameter) = Diameter(this.length + other.length)

    /** Subtracts a diameter (component-wise). */
    operator fun minus(other: Diameter) = Diameter(this.length - other.length)

    override fun toString() = "⌀ $meters m"
}

/**
 * Geometric radius.
 *
 * Base dimension: [Length].
 *
 * Provides accessors for [circumference] and [area] (circle with this radius).
 */
@JvmInline
value class Radius(private val length: Length) : HasRadius {

    /** Radius in meters (m). */
    val meters get() = length.inMeters

    /** Radius in millimeters (mm). */
    val millimeters get() = length.inMillimeters

    /** Returns itself (already a radius). */
    override fun toRadius() = this

    /** Converts to the corresponding [Diameter]. */
    fun toDiameter() = Diameter(length * 2.0)

    /** Circle circumference for this radius: `C = 2πr`. */
    fun circumference() = 2.0 * PI * meters

    /** Circle area for this radius: `A = π·r²`. */
    fun area() = PI * meters * meters

    /** Adds two radii (component-wise). */
    operator fun plus(other: Radius) = Radius(this.length + other.length)

    /** Subtracts a radius (component-wise). */
    operator fun minus(other: Radius) = Radius(this.length - other.length)

    override fun toString() = "r=$meters m"
}

/** Fluent builders from [Length] to geometry. */
val Length.diameter get() = Diameter(this)
val Length.radius get() = Radius(this)

/** Fluent builders from unit wrappers to geometry. */
val Meter.diameter get() = this.toLength().diameter
val Meter.radius get() = this.toLength().radius
val Millimeter.diameter get() = this.toLength().diameter
val Millimeter.radius get() = this.toLength().radius