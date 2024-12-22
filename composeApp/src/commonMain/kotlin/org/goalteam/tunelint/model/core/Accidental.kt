package org.goalteam.tunelint.model.core

sealed class Accidental private constructor(
    val pitchOffset: Int,
) {
    class Sharp : Accidental(1)

    class Natural : Accidental(0)

    class Flat : Accidental(-1)

    override fun equals(other: Any?) =
        other != null &&
            other is Accidental &&
            other.pitchOffset == pitchOffset

    override fun hashCode() = pitchOffset.hashCode()
}
