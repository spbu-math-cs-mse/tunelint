package org.goalteam.tunelint.model.changerequest.impl

import org.goalteam.tunelint.model.changerequest.ChangeRequest
import org.goalteam.tunelint.model.changerequest.ChangeRequestFactory
import org.goalteam.tunelint.model.changerequest.Notifiable
import org.goalteam.tunelint.model.changerequest.RequestableMelody
import org.goalteam.tunelint.model.core.Melody

class RequestableMelodyImpl(
    private val melody: Melody,
) : RequestableMelody {
    private val subscribers = mutableListOf<Notifiable<ChangeRequest>>()

    override fun subscribe(subscriber: Notifiable<ChangeRequest>) {
        subscribers.add(subscriber)
    }

    override fun unsubscribe(subscriber: Notifiable<ChangeRequest>) {
        subscribers.remove(subscriber)
    }

    override fun synchronize(caller: Notifiable<ChangeRequest>) {
        caller.notify(ChangeRequestFactory().synchronizationRequest(melody))
    }

    override fun notify(notification: ChangeRequest) {
        notification.execute(melody)
        subscribers.forEach { it.notify(notification) }
    }
}
