package org.goalteam.tunelint.filesupport

import org.goalteam.tunelint.property.Property

class ParserProperty(
    private val parser: Parser,
) : Property<Parser> {
    override fun identifier(): String = "parser"

    override fun value(): Parser = parser
}
