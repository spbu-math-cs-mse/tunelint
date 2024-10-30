package org.goalteam.tunelint.model.notifications

interface Notifiable<in T> {
    fun notify(notification: T)
}
