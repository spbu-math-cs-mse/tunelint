package org.goalteam.tunelint.interaction

interface Receiver {
    fun handleButton(button: CommandButtonInteractionData)

    fun handleAction(action: StaffInteractionData)
}
