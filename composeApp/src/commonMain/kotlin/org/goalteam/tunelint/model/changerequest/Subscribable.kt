package org.goalteam.tunelint.model.changerequest

interface Subscribable<out T> {
    fun subscribe(subscriber: Notifiable<T>)

    fun unsubscribe(subscriber: Notifiable<T>)

    fun synchronize(caller: Notifiable<T>)
}

fun <T> Subscribable<T>.subscribeAndSynchronize(subscriber: Notifiable<T>) {
    subscribe(subscriber)
    synchronize(subscriber)
}
