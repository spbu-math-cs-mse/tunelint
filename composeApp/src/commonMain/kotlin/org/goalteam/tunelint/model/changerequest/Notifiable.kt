package org.goalteam.tunelint.model.changerequest

interface Notifiable<in T> {
    fun notify(notification: T): Boolean
}
