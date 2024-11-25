package org.goalteam.tunelint.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import org.goalteam.tunelint.interaction.Interactor
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
    val interactor = Interactor(musicSheet.persistenceManager)
    val melody: MelodyViewable by mutableStateOf(
        MelodyViewableImpl(
            MusicFactory().createMelody("", TimeSignature.standardTime),
        ),
    )

    init {
        val viewable = ViewableMusicFactory().melody(melody)
        musicSheet.persistenceManager.subscribableMelody.subscribeAndSynchronize(viewable)
        musicSheet.persistenceManager.notify(PushFrontEmptyMeasurePersistentRequest())
    }
}
