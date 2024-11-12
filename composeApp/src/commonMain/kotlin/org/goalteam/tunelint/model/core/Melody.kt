package org.goalteam.tunelint.model.core

import org.goalteam.tunelint.model.musicsheetchangerequest.MusicSheetChangeRequest
import org.goalteam.tunelint.model.notifications.Notifiable
import org.goalteam.tunelint.model.notifications.Subscribable

interface Melody :
    Subscribable<MusicSheetChangeRequest>,
    Notifiable<MusicSheetChangeRequest> {
    fun contents(): MutableList<Measure>

    fun modified(): Boolean

    fun makeDirty()
}
