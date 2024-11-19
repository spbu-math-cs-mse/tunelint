package org.goalteam.tunelint.model.core.impl

import org.goalteam.tunelint.model.core.PrimaryNoteValue
import org.goalteam.tunelint.model.core.Rest

class RestImpl(
    private val primaryValue: PrimaryNoteValue,
) : Rest {
    override fun value() = primaryValue.value()

    override fun primaryValue() = primaryValue

    override fun clone() = RestImpl(primaryValue)

    override fun equals(other: Any?): Boolean =
        other != null &&
            other is Rest &&
            other.value() == value()

    override fun hashCode() = value().hashCode()
}
