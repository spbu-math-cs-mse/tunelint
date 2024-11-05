package org.goalteam.tunelint.model.musicsheetcontainer

import org.goalteam.tunelint.model.core.Measure
import org.goalteam.tunelint.model.core.MusicFactory
import org.goalteam.tunelint.model.musicsheetchangerequest.MusicSheetChangeRequest
import org.goalteam.tunelint.model.notifications.Notifiable
import java.nio.file.Path
import java.util.LinkedList
import kotlin.io.path.readLines
import kotlin.io.path.writeText

internal class MusicSheetImpl(
    private val path: Path,
) : MusicSheet {
    private val subscribers = mutableListOf<Notifiable<MusicSheetChangeRequest>>()
    private val measures = LinkedList<Measure>()
    private val commands = mutableListOf<MusicSheetChangeRequest>()
    private var modified = false

    override fun contents(): Collection<Any> = measures

    override fun save() {
        measures.joinToString(separator = System.lineSeparator()) { it.toString() }.run { path.writeText(this) }
        modified = false
    }

    override fun load() {
        measures.clear()
        measures.add(
            MusicFactory().createMeasure(
                path
                    .readLines()
                    .map { it.toInt() }
                    .map { MusicFactory().createNote(it, 1f) },
            ),
        )
        modified = false
    }

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
