package org.goalteam.tunelint.model.musicsheetchangerequest

interface MusicSheetChangeRequest {
    override fun toString(): String

    fun execute()

    fun isExecutable(): Boolean

    fun revert()

    fun isRevertible(): Boolean
}
