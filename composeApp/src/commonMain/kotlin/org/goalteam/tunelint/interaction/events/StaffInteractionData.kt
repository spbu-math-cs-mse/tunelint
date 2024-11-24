package org.goalteam.tunelint.interaction.events

import org.goalteam.tunelint.model.core.NoteOffset

interface StaffInteractionData {
    fun stage(): NoteOffset

    fun position(): Int

    fun measure(): Int

    fun side(): Side

    fun action(): Action
}
