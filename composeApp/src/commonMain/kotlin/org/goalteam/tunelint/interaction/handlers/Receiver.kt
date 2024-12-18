package org.goalteam.tunelint.interaction.handlers

import org.goalteam.tunelint.interaction.events.CommandButtonInteractionData
import org.goalteam.tunelint.interaction.events.StaffInteractionData

/**
 * Handler for model-related events coming from user.
 * Use this via Interactor not just by itself
 */
interface Receiver {
    fun handleButton(button: CommandButtonInteractionData)

    fun handleAction(action: StaffInteractionData)
}
