package org.goalteam.tunelint.lint.status

data class Ok(
    val message: String,
) : Status {
    override fun at(): Collection<Any> = emptyList()

    override fun message(): String = message
}
