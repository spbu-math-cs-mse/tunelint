package org.goalteam.tunelint.model.core

import org.goalteam.tunelint.model.changerequest.ChangeRequest
import org.goalteam.tunelint.model.notifications.Notifiable

interface Measure : Notifiable<ChangeRequest<MutableMeasure>> {
    fun symbols(): List<Symbol>
}
