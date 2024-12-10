package org.goalteam.tunelint.lint.status

data class Warning(
    val at: Collection<Any>,
    val message: String,
) : Status {
    override fun at(): Collection<Any> = at

    override fun message(): String = message
}
