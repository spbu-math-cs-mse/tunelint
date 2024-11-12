package org.goalteam.tunelint.model.core.impl

import org.goalteam.tunelint.model.core.Measure
import org.goalteam.tunelint.model.core.Melody
import org.goalteam.tunelint.model.musicsheetchangerequest.MusicSheetChangeRequest
import org.goalteam.tunelint.model.notifications.Notifiable

internal class MelodyImpl(
    private val measures: List<Measure>,
) : Melody {
    private val subscribers = mutableListOf<Notifiable<MusicSheetChangeRequest>>()

    private val commands = mutableListOf<MusicSheetChangeRequest>()
    private var modified = false

    override fun contents(): MutableList<Measure> = measures.toMutableList()

    override fun modified() = modified

    override fun makeDirty() {
        modified = true
    }

    override fun subscribe(subscriber: Notifiable<MusicSheetChangeRequest>) {
        subscribers.add(subscriber)
    }

    override fun unsubscribe(subscriber: Notifiable<MusicSheetChangeRequest>) {
        subscribers.remove(subscriber)
    }

    override fun notify(notification: MusicSheetChangeRequest) {
        if (notification.isExecutable()) {
            notification.execute()
        } else {
            println("bad request")
            return
        }
        makeDirty()
        commands.add(notification)
        subscribers.forEach { it.notify(notification) }
    }
}
