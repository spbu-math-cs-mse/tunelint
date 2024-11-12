package org.goalteam.tunelint.model.core

import org.goalteam.tunelint.model.changerequest.ChangeRequest
import org.goalteam.tunelint.model.notifications.Notifiable
import org.goalteam.tunelint.model.notifications.Subscribable

interface Melody :
    Subscribable<ChangeRequest<MutableMelody>>,
    Notifiable<ChangeRequest<MutableMelody>> {
    fun name(): String

    fun measures(): List<Measure>

    fun modified(): Boolean // TODO move to musicSheet

    fun makeDirty() // TODO move to musicSheet
}
