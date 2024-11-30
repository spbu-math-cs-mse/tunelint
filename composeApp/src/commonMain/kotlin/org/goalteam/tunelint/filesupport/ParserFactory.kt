package org.goalteam.tunelint.filesupport

class ParserFactory {
    fun xml(): ParserProperty = ParserProperty(XMLParser())

    fun fake(): ParserProperty = ParserProperty(FakeParser())
}
