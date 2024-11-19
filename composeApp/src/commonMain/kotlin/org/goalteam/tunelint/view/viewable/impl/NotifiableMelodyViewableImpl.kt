package org.goalteam.tunelint.view.viewable.impl

import org.goalteam.tunelint.model.changerequest.ChangeRequest
import org.goalteam.tunelint.model.changerequest.Notifiable
import org.goalteam.tunelint.view.viewable.MelodyViewable

class NotifiableMelodyViewableImpl(
    private val melodyViewable: MelodyViewable,
) : Notifiable<ChangeRequest> {
    override fun notify(notification: ChangeRequest) {
        notification.execute(melodyViewable)
    }
}
