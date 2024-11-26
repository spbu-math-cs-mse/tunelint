package org.goalteam.tunelint.view.musicsheet.viewable

import org.goalteam.tunelint.model.changerequest.ChangeRequest
import org.goalteam.tunelint.model.changerequest.Notifiable
import org.goalteam.tunelint.view.musicsheet.viewable.impl.MelodyViewableImpl
import org.goalteam.tunelint.view.musicsheet.viewable.impl.NotifiableMelodyViewableImpl

class ViewableMusicFactory {
    fun melody(melodyViewable: MelodyViewable): Notifiable<ChangeRequest> =
        NotifiableMelodyViewableImpl(
            MelodyViewableImpl(melodyViewable),
        )
}
