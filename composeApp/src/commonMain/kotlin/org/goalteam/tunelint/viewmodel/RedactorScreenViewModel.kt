package org.goalteam.tunelint.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import org.goalteam.tunelint.model.core.Note
import org.goalteam.tunelint.model.core.NoteImpl
import org.goalteam.tunelint.model.musicsheetchangerequest.MusicSheetChangeRequest
import org.goalteam.tunelint.model.musicsheetchangerequest.MusicSheetChangeRequestStub
import org.goalteam.tunelint.model.musicsheetcontainer.MusicSheet
import org.goalteam.tunelint.model.notifications.Notifiable

class RedactorScreenViewModel(
    private val container: MusicSheet,
) : ViewModel() {
    // TODO: create RedactorScreenViewModel

    class MusicSheetChangeInfoAdapter(
        container: MusicSheet,
        private val viewModel: RedactorScreenViewModel,
    ) : Notifiable<MusicSheetChangeRequest> {
        init {
            container.subscribe(this)
        }

        override fun notify(notification: MusicSheetChangeRequest) {
            viewModel.stateChange(notification)
        }
    }

    private var count = 0
    private val adapter = MusicSheetChangeInfoAdapter(container, this)
    private val _state = mutableStateListOf<Note>()
    val state = _state

    fun interactionEvent() {
        container.notify(MusicSheetChangeRequestStub(NoteImpl(count)))
        count++
    }

    fun stateChange(notification: MusicSheetChangeRequest) {
        notification.execute(_state)
    }
}
