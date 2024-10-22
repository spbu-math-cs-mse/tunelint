package org.goalteam.tunelint.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import org.goalteam.tunelint.model.musicsheetchangeinfo.MusicSheetChangeInfo
import org.goalteam.tunelint.model.musicsheetchangerequest.MusicSheetChangeRequestStub
import org.goalteam.tunelint.model.musicsheetcontainer.MusicSheetContainer
import org.goalteam.tunelint.model.notifications.Notifiable

class RedactorScreenViewModel(
    private val container: MusicSheetContainer,
) : ViewModel() {
    // TODO: create RedactorScreenViewModel

    class MusicSheetChangeInfoAdapter(
        container: MusicSheetContainer,
        private val viewModel: RedactorScreenViewModel,
    ) : Notifiable<MusicSheetChangeInfo> {
        init {
            container.subscribe(this)
        }

        override fun notify(notificationInfo: MusicSheetChangeInfo) {
            viewModel.stateChange(notificationInfo.toString())
        }
    }

    private var count = 0
    private val adapter = MusicSheetChangeInfoAdapter(container, this)
    private val _state = MutableStateFlow("")
    val state: StateFlow<String> = _state.asStateFlow()

    fun interactionEvent() {
        container.notify(MusicSheetChangeRequestStub("count: $count"))
        count++
    }

    fun stateChange(newState: String) {
        _state.update { newState }
    }
}
