package org.goalteam.tunelint.model.core

import org.goalteam.tunelint.model.changerequest.ChangeRequest
import org.goalteam.tunelint.model.notifications.Notifiable

interface Melody : Notifiable<ChangeRequest<MutableMelody>> {
    fun name(): String

    fun measures(): List<Measure>
}
