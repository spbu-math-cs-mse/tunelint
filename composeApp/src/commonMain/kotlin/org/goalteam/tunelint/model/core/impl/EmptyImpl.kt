package org.goalteam.tunelint.model.core.impl

import org.goalteam.tunelint.model.core.Empty

class EmptyImpl(
    private val length: Float,
) : Empty {
    override fun length() = length

    override fun equals(other: Any?): Boolean =
        other != null &&
            other is Empty &&
            other.length() == length
}
