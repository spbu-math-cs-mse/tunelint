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

    @Test
    fun test_3() {
        val melodyList = parser.readMusic("././././././music/parser_test_input_3.xml")
        assertEquals(melodyList.size, 1)
        parser.writeMusic("././././././music/parser_test_output_3.xml", melodyList)
    }

    @Test
    fun test_4() {
        val melodyList = parser.readMusic("././././././music/parser_test_input_4.xml")
        assertEquals(melodyList.size, 0)
        parser.writeMusic("././././././music/parser_test_output_4.xml", melodyList)
    }

    @Test
    fun test_5() {
        val melodyList = parser.readMusic("././././././music/parser_test_input_5.xml")
        assertEquals(melodyList.size, 3)
        parser.writeMusic("././././././music/parser_test_output_5.xml", melodyList)
    }
}
