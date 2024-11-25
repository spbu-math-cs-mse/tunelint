@file:Suppress("ktlint:standard:filename")

package org.goalteam.tunelint.filesupport

import org.goalteam.tunelint.model.core.*
import org.jdom2.Document
import org.jdom2.output.Format
import org.jdom2.output.XMLOutputter
import kotlin.test.Test
import kotlin.test.assertEquals
import java.io.File

class XMLParserTest {
    private val parser = XMLParser()

    @Test
    fun test_1() {
        val melodyList = parser.readMusic("././././././music/parser_test_input_1.xml")
        assertEquals(melodyList.size, 1)
        parser.writeMusic("././././././music/parser_test_output_1.xml", melodyList)
    }
}
