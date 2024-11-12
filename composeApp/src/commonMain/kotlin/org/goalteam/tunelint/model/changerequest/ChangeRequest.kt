package org.goalteam.tunelint.model.changerequest

interface ChangeRequest<in SubjectType> {
    override fun toString(): String

    fun execute(subject: SubjectType)

    fun isExecutable(): Boolean

    fun revert(subject: SubjectType)

    fun isRevertible(): Boolean
}
