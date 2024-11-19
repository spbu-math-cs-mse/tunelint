package org.goalteam.tunelint.model.changerequest

interface ChangeRequest<in SubjectType> {
    override fun toString(): String

    fun execute()

    fun isExecutable(): Boolean

    fun revert()

    fun isRevertible(): Boolean
}
