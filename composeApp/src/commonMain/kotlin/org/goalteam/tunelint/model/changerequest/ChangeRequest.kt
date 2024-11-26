package org.goalteam.tunelint.model.changerequest

import org.goalteam.tunelint.model.core.Melody

interface ChangeRequest {
    override fun toString(): String

    fun execute(melody: Melody): Boolean
}
