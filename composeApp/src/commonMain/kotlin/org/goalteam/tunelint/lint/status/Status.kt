package org.goalteam.tunelint.lint.status

interface Status {
    fun at(): Collection<Any>

    fun message(): String
}
