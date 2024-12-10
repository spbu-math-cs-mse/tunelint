package org.goalteam.tunelint.lint.status

class Ok(
    private val message: String,
) : Status {
    override fun at(): Collection<Any> = emptyList()

    override fun message(): String = message
}
