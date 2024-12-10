package org.goalteam.tunelint.lint.status

class Warning(
    private val at: Collection<Any>,
    private val message: String,
) : Status {
    override fun at(): Collection<Any> = at

    override fun message(): String = message
}
