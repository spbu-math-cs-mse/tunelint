package org.goalteam.tunelint.filesupport

import org.goalteam.tunelint.model.core.ImmutableMeasure
import org.goalteam.tunelint.model.core.ImmutableMelody
import org.goalteam.tunelint.model.core.Measure
import org.goalteam.tunelint.model.core.Melody
import org.goalteam.tunelint.model.core.MusicFactory
import org.goalteam.tunelint.model.core.Note
import org.goalteam.tunelint.model.core.PrimaryNoteValue
import org.goalteam.tunelint.model.core.Symbol
import org.goalteam.tunelint.model.core.TimeSignature
import org.jdom2.Document
import org.jdom2.Element
import org.jdom2.input.SAXBuilder
import org.jdom2.output.Format
import org.jdom2.output.XMLOutputter
import java.io.File

class XMLParser : Parser {
    private val musicFactory = MusicFactory()

    private fun readNote(note: Element): Symbol? {
        val pitch = note.getChildText("pitch").toIntOrNull()
        val value = note.getChildText("value").toIntOrNull() ?: return null
        return if (pitch == null) {
            musicFactory.createRest(PrimaryNoteValue(value))
        } else {
            musicFactory.createNote(pitch, PrimaryNoteValue(value))
        }
    }

    private fun readMeasure(measure: Element): Measure? {
        val measureNumber = measure.getAttributeValue("number")
        println("  Measure number: $measureNumber")

        val noteList = mutableListOf<Symbol>()

        val notes = measure.getChildren("note")
        notes.forEach { note ->
            val noteModel = readNote(note)
            if (noteModel != null) {
                noteList.add(noteModel)
            } else {
                println(
                    "\\033[31m fatal error:\\033[0m error occurred in measure $measureNumber",
                )
                return null
            }
        }
        return musicFactory.createMeasure(TimeSignature.standardTime, noteList)
    }

    private fun readMelody(melody: Element): Melody? {
        val melodyId = melody.getAttributeValue("id")
        println("Processing melody: $melodyId")

        val measureList = mutableListOf<Measure>()

        val measures = melody.getChildren("measure")
        measures.forEach { measure ->
            val measureModel = readMeasure(measure)
            if (measureModel != null) {
                measureList.add(measureModel)
            } else {
                println(
                    "\\033[31m fatal error:\\033[0m failed to parse melody $melodyId",
                )
                return null
            }
        }
        return musicFactory.createMelody(melodyId, TimeSignature.standardTime, measureList)
    }

    override fun readMusic(path: String): List<Melody> {
        val file = File(path)
        val saxBuilder = SAXBuilder()
        val document: Document = saxBuilder.build(file)
        val rootElement: Element = document.rootElement

        val workTitle = rootElement.getChild("work")?.getChildText("work-title")

        val partList = rootElement.getChild("part-list")?.getChildren("score-part")
        partList?.forEach { part ->
            val partName = part.getChildText("part-name")
        }

        val melodyList = mutableListOf<Melody>()

        val melodies = rootElement.getChildren("melody")
        melodies.forEach { melody ->
            val melodyModel = readMelody(melody)
            if (melodyModel != null) {
                melodyList.add(melodyModel)
            }
        }
        return melodyList
    }

    private fun writeNote(symbol: Symbol): Element {
        val element = Element("note")
        val value = Element("value").setText(symbol.value().toString())
        element.addContent(value)
        if (symbol is Note) {
            val pitch = Element("pitch").setText(symbol.stage().toString())
            element.addContent(pitch)
        }

        return element
    }

    private fun writeMeasure(measure: ImmutableMeasure): Element {
        val element = Element("measure")
        measure.symbols.forEach { symbol ->
            element.addContent(writeNote(symbol))
        }
        return element
    }

    private fun writeMelody(melody: ImmutableMelody): Element {
        val element = Element("melody")
        melody.measures.forEachIndexed { i, measure ->
            element.addContent(writeMeasure(measure).setAttribute("number", "${i + 1}"))
        }
        return element
    }

    override fun writeMusic(
        path: String,
        melodyList: List<Melody>,
    ) {
        val file = File(path)
        val scorePartwise = Element("score-partwise").setAttribute("version", "3.1")

        val work = Element(file.nameWithoutExtension)
        work.addContent(Element("work-title").setText(file.nameWithoutExtension))
        scorePartwise.addContent(work)

        val partList = Element("melody-list")
        for (i in melodyList.indices) {
            val scorePart = Element("score-part").setAttribute("id", "P${i + 1}")
            scorePart.addContent(Element("part-name").setText("part ${i + 1}"))
            partList.addContent(scorePart)
        }
        scorePartwise.addContent(partList)

        melodyList.forEachIndexed { i, melody ->
            scorePartwise.addContent(writeMelody(melody).setAttribute("id", "P${i + 1}"))
        }

        XMLOutputter(Format.getPrettyFormat()).output(Document(scorePartwise), file.writer())

        println("XML-file created successfully in path: ${file.absolutePath}")
    }
}
