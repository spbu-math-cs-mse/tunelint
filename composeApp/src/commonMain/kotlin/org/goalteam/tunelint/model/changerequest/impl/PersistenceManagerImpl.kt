package org.goalteam.tunelint.model.changerequest.impl

import org.goalteam.tunelint.model.changerequest.Notifiable
import org.goalteam.tunelint.model.changerequest.PersistenceManager
import org.goalteam.tunelint.model.changerequest.PersistentRequest
import org.goalteam.tunelint.model.changerequest.RequestableMelody
import org.goalteam.tunelint.model.changerequest.SubscribableMelody
import org.goalteam.tunelint.model.changerequest.UndoRedoAvailable
import java.util.Stack

class PersistenceManagerImpl(
    private val requestableMelody: RequestableMelody,
) : PersistenceManager {
    override val subscribableMelody = requestableMelody as SubscribableMelody

    private val executed = Stack<PersistentRequest>()
    private val reverted = Stack<PersistentRequest>()
    private val subscribers : MutableList<Notifiable<UndoRedoAvailable>> = mutableListOf()

    private fun sendUndoRedoNotification(){
        val notification = UndoRedoAvailable(undoAvailable(), redoAvailable())
        for(subscriber in subscribers) {
            subscriber.notify(notification)
        }
    }

    override fun undo() {
        val lastExecuted = executed.peek()
        executed.pop()
        requestableMelody.notify(lastExecuted.reverseRequest)
        reverted.push(lastExecuted)
        sendUndoRedoNotification()
    }

    private fun undoAvailable(): Boolean = executed.isNotEmpty()

    override fun redo() {
        val lastReverted = reverted.peek()
        reverted.pop()
        requestableMelody.notify(lastReverted.directRequest)
        executed.push(lastReverted)
        sendUndoRedoNotification()
    }

    private fun redoAvailable(): Boolean = reverted.isNotEmpty()

    override fun notify(notification: PersistentRequest) {
        reverted.clear()
        executed.push(notification)
        requestableMelody.notify(notification.directRequest)
        sendUndoRedoNotification()
    }

    override fun subscribe(subscriber: Notifiable<UndoRedoAvailable>) {
        subscribers.add(subscriber)
    }

    override fun unsubscribe(subscriber: Notifiable<UndoRedoAvailable>) {
        subscribers.remove(subscriber)
    }

    override fun synchronize(caller: Notifiable<UndoRedoAvailable>) {
        caller.notify(UndoRedoAvailable(undoAvailable(), redoAvailable()))
    }
}
