package org.goalteam.tunelint.model.notifications

interface Subscribable<out T> {
    fun subscribe(subscriber: Notifiable<T>)

    fun unsubscribe(subscriber: Notifiable<T>)
}
