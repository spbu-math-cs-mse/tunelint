package org.goalteam.tunelint.lint

import org.goalteam.tunelint.lint.status.Status

interface Rule {
    fun check(): Status
}
