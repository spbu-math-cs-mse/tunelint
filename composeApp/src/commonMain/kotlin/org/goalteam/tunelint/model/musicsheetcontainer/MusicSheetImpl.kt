package org.goalteam.tunelint.model.musicsheetcontainer

import org.goalteam.tunelint.model.core.MusicFactory
import org.goalteam.tunelint.model.core.Note
import org.goalteam.tunelint.model.musicsheetchangeinfo.MusicSheetChangeInfo
import org.goalteam.tunelint.model.musicsheetchangeinfo.MusicSheetChangeInfoStub
import org.goalteam.tunelint.model.musicsheetchangerequest.MusicSheetChangeRequest
import org.goalteam.tunelint.model.notifications.Notifiable
import java.nio.file.Path
import java.util.LinkedList
import kotlin.io.path.readLines
import kotlin.io.path.writeText

internal class MusicSheetImpl(
    private val path: Path,
) : MusicSheet {
    private val subscribers = mutableListOf<Notifiable<MusicSheetChangeInfo>>()
    private val notes = LinkedList<Note>()
    private val commands = mutableListOf<MusicSheetChangeRequest>()
    private var modified = false

    override fun contents(): Collection<Any> = notes

    override fun save() {
        notes.joinToString(separator = System.lineSeparator()) { it.toString() }.run { path.writeText(this) }
        modified = false
    }

    override fun load() {
        notes.clear()
        path
            .readLines()
            .map { it.toInt() }
            .map { MusicFactory().createNote(it) }
            .forEach { notes.add(it) }
        modified = false
    }

    override fun modified() = modified

    override fun makeDirty() {
        modified = true
    }

    override fun subscribe(subscriber: Notifiable<MusicSheetChangeInfo>) {
        subscribers.add(subscriber)
    }

    override fun unsubscribe(subscriber: Notifiable<MusicSheetChangeInfo>) {
        subscribers.remove(subscriber)
    }

    override fun notify(notificationInfo: MusicSheetChangeRequest) {
        subscribers.forEach {
            it.notify(MusicSheetChangeInfoStub("$notificationInfo"))
        }
    }
}
