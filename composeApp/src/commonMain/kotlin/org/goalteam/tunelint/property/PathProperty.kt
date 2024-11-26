package org.goalteam.tunelint.property

class PathProperty(
    private val path: String,
) : Property<String> {
    override fun identifier(): String = "path"

    override fun value(): String = path
}
