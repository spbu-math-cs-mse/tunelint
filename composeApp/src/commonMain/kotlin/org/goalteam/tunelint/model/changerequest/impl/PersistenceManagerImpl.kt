package org.goalteam.tunelint.model.changerequest.impl

import org.goalteam.tunelint.model.changerequest.PersistenceManager
import org.goalteam.tunelint.model.changerequest.PersistentRequest
import org.goalteam.tunelint.model.changerequest.RequestableMelody
import org.goalteam.tunelint.model.changerequest.SubscribableMelody
import java.util.Stack

class PersistenceManagerImpl(
    private val requestableMelody: RequestableMelody,
) : PersistenceManager {
    override val subscribableMelody = requestableMelody as SubscribableMelody

    private val executed = Stack<PersistentRequest>()
    private val reverted = Stack<PersistentRequest>()

    override fun undo() {
        val lastExecuted = executed.peek()
        executed.pop()
        requestableMelody.notify(lastExecuted.reverseRequest)
        reverted.push(lastExecuted)
    }

    override fun redo() {
        val lastReverted = reverted.peek()
        reverted.pop()
        requestableMelody.notify(lastReverted.directRequest)
        executed.push(lastReverted)
    }

    override fun notify(notification: PersistentRequest) {
        reverted.clear()
        executed.push(notification)
        requestableMelody.notify(notification.directRequest)
    }
}
