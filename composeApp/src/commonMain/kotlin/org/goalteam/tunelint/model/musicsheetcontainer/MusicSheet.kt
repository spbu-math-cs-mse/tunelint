package org.goalteam.tunelint.model.musicsheetcontainer

import org.goalteam.tunelint.model.musicsheetchangeinfo.MusicSheetChangeInfo
import org.goalteam.tunelint.model.musicsheetchangerequest.MusicSheetChangeRequest
import org.goalteam.tunelint.model.notifications.Notifiable
import org.goalteam.tunelint.model.notifications.Subscribable

interface MusicSheet :
    Subscribable<MusicSheetChangeInfo>,
    Notifiable<MusicSheetChangeRequest> {
    fun contents(): Collection<Any>

    fun save()

    fun load()

    fun modified(): Boolean

    fun makeDirty()
}