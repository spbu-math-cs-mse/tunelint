package org.goalteam.tunelint.model.core.impl

import org.goalteam.tunelint.model.core.Rest

class RestImpl(
    private val value: Int,
) : Rest {
    override fun value() = value

    override fun equals(other: Any?): Boolean =
        other != null &&
            other is Rest &&
            other.value() == value
}
