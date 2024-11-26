@file:Suppress("ktlint:standard:filename")

package org.goalteam.tunelint.filesupport

import kotlin.test.Test
import kotlin.test.assertEquals

class XMLParserTest {
    private val parser = XMLParser()

    @Test
    fun test_1() {
        val melodyList = parser.readMusic("././././././music/parser_test_input_1.xml")
        assertEquals(melodyList.size, 1)
        parser.writeMusic("././././././music/parser_test_output_1.xml", melodyList)
    }

    @Test
    fun test_2() {
        val melodyList = parser.readMusic("././././././music/parser_test_input_2.xml")
        assertEquals(melodyList.size, 2)
        parser.writeMusic("././././././music/parser_test_output_2.xml", melodyList)
    }
}
