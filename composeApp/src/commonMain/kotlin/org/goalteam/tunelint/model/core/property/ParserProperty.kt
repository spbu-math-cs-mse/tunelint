package org.goalteam.tunelint.model.core.property

import org.goalteam.tunelint.filesupport.Parser

class ParserProperty(
    private val parser: Parser,
) : Property<Parser> {
    override fun identifier(): String = "parser"

    override fun value(): Parser = parser
}
