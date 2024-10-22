package org.goalteam.tunelint.model.musicsheetcontainer

import org.goalteam.tunelint.model.musicsheetchangeinfo.MusicSheetChangeInfo
import org.goalteam.tunelint.model.musicsheetchangeinfo.MusicSheetChangeInfoStub
import org.goalteam.tunelint.model.musicsheetchangerequest.MusicSheetChangeRequest
import org.goalteam.tunelint.model.notifications.Notifiable

class MusicSheetContainerStub : MusicSheetContainer {
    private val subscribers = mutableListOf<Notifiable<MusicSheetChangeInfo>>()

    override fun subscribe(subscriber: Notifiable<MusicSheetChangeInfo>) {
        subscribers.add(subscriber)
    }

    override fun unsubscribe(subscriber: Notifiable<MusicSheetChangeInfo>) {
        subscribers.remove(subscriber)
    }

    override fun notify(notificationInfo: MusicSheetChangeRequest) {
        subscribers.forEach {
            it.notify(MusicSheetChangeInfoStub("$notificationInfo"))
        }
    }
}
