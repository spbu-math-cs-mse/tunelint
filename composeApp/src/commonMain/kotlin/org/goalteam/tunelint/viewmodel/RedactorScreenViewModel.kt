package org.goalteam.tunelint.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import org.goalteam.tunelint.model.musicsheetchangerequest.MusicSheetChangeRequest
import org.goalteam.tunelint.model.musicsheetcontainer.Commands
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
            viewModel.stateChange(notification.toString())
        }
    }

    private var count = 0
    private val adapter = MusicSheetChangeInfoAdapter(container, this)
    private val _state = MutableStateFlow("")
    val state: StateFlow<String> = _state.asStateFlow()

    fun interactionEvent() {
        container.notify(Commands(container).addNote(0, 0, 0, 0f))
        count++
    }

    fun stateChange(newState: String) {
        _state.update { newState }
    }
}
