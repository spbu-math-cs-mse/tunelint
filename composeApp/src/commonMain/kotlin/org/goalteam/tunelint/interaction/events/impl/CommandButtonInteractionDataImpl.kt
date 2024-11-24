package org.goalteam.tunelint.interaction.events.impl

import org.goalteam.tunelint.interaction.events.CommandButtonInteractionData
import org.goalteam.tunelint.interaction.events.CommandType

class CommandButtonInteractionDataImpl(
    private val commandType: CommandType,
) : CommandButtonInteractionData {
    override fun command(): CommandType = commandType
}
