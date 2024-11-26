package org.goalteam.tunelint.model.changerequest

interface PersistenceManager : Notifiable<PersistentRequest>, Subscribable<UndoRedoAvailable> {
    val subscribableMelody: SubscribableMelody

    fun undo()

    fun redo()
}

data class UndoRedoAvailable(val undoAvailable: Boolean, val redoAvailable: Boolean)


