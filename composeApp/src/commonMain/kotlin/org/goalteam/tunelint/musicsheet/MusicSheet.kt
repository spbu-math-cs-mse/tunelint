package org.goalteam.tunelint.musicsheet

import org.goalteam.tunelint.filesupport.Parser
import org.goalteam.tunelint.filesupport.XMLParser
import org.goalteam.tunelint.model.changerequest.ChangeRequest
import org.goalteam.tunelint.model.core.MusicFactory
import org.goalteam.tunelint.model.core.MutableMelody
import org.goalteam.tunelint.model.core.property.ParserProperty
import org.goalteam.tunelint.model.core.property.Property
import org.goalteam.tunelint.model.notifications.Notifiable
import org.goalteam.tunelint.model.notifications.Subscribable
import java.io.IOException

class MusicSheet(
    private val path: String,
) : Subscribable<ChangeRequest<MutableMelody>>,
    Notifiable<ChangeRequest<MutableMelody>> {
    private var melody: MutableMelody = MusicFactory().createMelody("", listOf())
    private val subscribers = mutableListOf<Notifiable<ChangeRequest<MutableMelody>>>()
    private val commands = mutableListOf<ChangeRequest<MutableMelody>>()
    private var modified = false

    /**
     * @param properties collection of properties, for example ParserProperty
     */
    fun load(properties: Collection<Property<*>>) {
        val read = parser(properties).readMusic(path).firstOrNull()
        if (read != null) {
            melody = read as MutableMelody
        } else {
            throw IOException("Couldn't load $path")
        }
    }

    /**
     * @param properties collection of properties, for example ParserProperty
     */
    fun save(properties: Collection<Property<*>>) {
        parser(properties).writeMusic(path, mutableListOf(melody))
    }

    fun modified() = modified

    fun makeDirty() {
        modified = true
    }

    override fun subscribe(subscriber: Notifiable<ChangeRequest<MutableMelody>>) {
        subscribers.add(subscriber)
    }

    override fun unsubscribe(subscriber: Notifiable<ChangeRequest<MutableMelody>>) {
        subscribers.remove(subscriber)
    }

    private fun parser(properties: Collection<Property<*>>): Parser =
        properties.filterIsInstance<ParserProperty>().map { it.value() }.firstOrNull() ?: XMLParser()

    override fun notify(notification: ChangeRequest<MutableMelody>) {
        if (notification.isExecutable()) {
            notification.execute()
        } else {
            println("bad request")
            return
        }
        commands.add(notification)
        subscribers.forEach { it.notify(notification) }
    }
}
