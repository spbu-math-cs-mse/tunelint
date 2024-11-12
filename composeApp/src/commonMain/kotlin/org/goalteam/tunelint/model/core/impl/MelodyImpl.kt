package org.goalteam.tunelint.model.core.impl

import org.goalteam.tunelint.model.changerequest.ChangeRequest
import org.goalteam.tunelint.model.core.Measure
import org.goalteam.tunelint.model.core.MutableMeasure
import org.goalteam.tunelint.model.core.MutableMelody
import org.goalteam.tunelint.model.notifications.Notifiable

internal class MelodyImpl(
    private val name: String,
    measures: List<MutableMeasure>,
) : MutableMelody {
    private val measures: MutableList<MutableMeasure> = measures.toMutableList()
    private val subscribers = mutableListOf<Notifiable<ChangeRequest<MutableMelody>>>()

    private val commands = mutableListOf<ChangeRequest<MutableMelody>>()
    private var modified = false

    override fun name(): String = this.name

    override fun measures(): List<Measure> = measures

    override fun measuresMut(): MutableList<MutableMeasure> = measures

    override fun modified() = modified

    override fun makeDirty() {
        modified = true
    }

    override fun subscribe(subscriber: Notifiable<ChangeRequest<MutableMelody>>) {
        subscribers.add(subscriber)
    }

    override fun unsubscribe(subscriber: Notifiable<ChangeRequest<MutableMelody>>) {
        subscribers.remove(subscriber)
    }

    override fun notify(notification: ChangeRequest<MutableMelody>) {
        if (notification.isExecutable()) {
            notification.execute(this as MutableMelody)
        } else {
            println("bad request")
            return
        }
        makeDirty()
        commands.add(notification)
        subscribers.forEach { it.notify(notification) }
    }
}
