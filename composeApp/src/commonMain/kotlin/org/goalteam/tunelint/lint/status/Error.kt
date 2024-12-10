package org.goalteam.tunelint.lint.status

data class Error(
    val at: Collection<Any>,
    val message: String,
) : Status {
    constructor(many: Collection<Error>) : this(
        many.flatMap { it.at },
        many.map { it.message }.distinct().joinToString("\n"),
    )

    override fun at(): Collection<Any> = at

    override fun message(): String = message
}
