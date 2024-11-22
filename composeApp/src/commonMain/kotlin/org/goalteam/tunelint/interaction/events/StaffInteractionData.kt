package org.goalteam.tunelint.interaction.events

interface StaffInteractionData {
    fun stage(): Int

    fun position(): Int

    fun measure(): Int

    fun side(): Side

    fun action(): Action
}
