package org.goalteam.tunelint.model.changerequest

interface PersistenceManager : Notifiable<PersistentRequest> {
    val subscribableMelody: SubscribableMelody

    fun undo()

    fun redo()
}
