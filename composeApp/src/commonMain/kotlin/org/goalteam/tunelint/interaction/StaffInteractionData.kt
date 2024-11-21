package org.goalteam.tunelint.interaction

interface StaffInteractionData {
    fun stage(): Int

    fun position(): Int

    fun measure(): Int

    fun action(): Action
}
