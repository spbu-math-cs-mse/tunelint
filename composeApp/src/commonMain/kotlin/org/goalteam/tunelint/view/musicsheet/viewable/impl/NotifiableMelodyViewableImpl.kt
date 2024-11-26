package org.goalteam.tunelint.view.musicsheet.viewable.impl

import org.goalteam.tunelint.model.changerequest.ChangeRequest
import org.goalteam.tunelint.model.changerequest.Notifiable
import org.goalteam.tunelint.view.musicsheet.viewable.MelodyViewable

class NotifiableMelodyViewableImpl(
    private val melodyViewable: MelodyViewable,
) : Notifiable<ChangeRequest> {
    override fun notify(notification: ChangeRequest): Boolean = notification.execute(melodyViewable)
}
