package org.goalteam.tunelint.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import org.goalteam.tunelint.interaction.impl.ReceiverImpl
import org.goalteam.tunelint.interaction.impl.RedactorConfigurationImpl
import org.goalteam.tunelint.model.changerequest.impl.PushFrontEmptyMeasurePersistentRequest
import org.goalteam.tunelint.model.changerequest.subscribeAndSynchronize
import org.goalteam.tunelint.model.core.MusicFactory
import org.goalteam.tunelint.model.core.TimeSignature
import org.goalteam.tunelint.musicsheet.MusicSheet
import org.goalteam.tunelint.view.viewable.MelodyViewable
import org.goalteam.tunelint.view.viewable.ViewableMusicFactory
import org.goalteam.tunelint.view.viewable.impl.MelodyViewableImpl

class RedactorScreenViewModel : ViewModel() {
    val musicSheet = MusicSheet("music/test.xml")
    val melody: MelodyViewable by mutableStateOf(
        MelodyViewableImpl(
            MusicFactory().createMelody("", TimeSignature.standardTime),
        ),
    )
    val mode = RedactorConfigurationImpl()
    val interactor = ReceiverImpl(mode, musicSheet.persistenceManager) // TODO make factories

    init {
        val viewable = ViewableMusicFactory().melody(melody)
        musicSheet.persistenceManager.subscribableMelody.subscribeAndSynchronize(viewable)
        musicSheet.persistenceManager.notify(PushFrontEmptyMeasurePersistentRequest())
    }
}
