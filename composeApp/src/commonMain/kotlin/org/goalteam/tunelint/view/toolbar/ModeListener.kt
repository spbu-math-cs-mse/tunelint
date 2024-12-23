package org.goalteam.tunelint.view.toolbar

import androidx.compose.runtime.MutableState
import org.goalteam.tunelint.interaction.events.Mode
import org.goalteam.tunelint.model.changerequest.Notifiable

internal class ModeListener(
    private val updated: () -> Mode,
    initial: MutableState<Mode>,
) : Notifiable<Unit> {
    private val current = initial

    override fun notify(notification: Unit): Boolean {
        current.value = updated()
        return true
    }

    fun current() = current.value
}
