package org.goalteam.tunelint.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import org.goalteam.tunelint.interaction.InteractorImpl
import org.goalteam.tunelint.model.changerequest.impl.PushFrontEmptyMeasurePersistentRequest
import org.goalteam.tunelint.model.changerequest.subscribeAndSynchronize
import org.goalteam.tunelint.model.core.MusicFactory
import org.goalteam.tunelint.model.core.TimeSignature
import org.goalteam.tunelint.musicsheet.MusicSheet
import org.goalteam.tunelint.view.musicsheet.viewable.MelodyViewable
import org.goalteam.tunelint.view.musicsheet.viewable.ViewableMusicFactory
import org.goalteam.tunelint.view.musicsheet.viewable.impl.MelodyViewableImpl

class RedactorScreenViewModel(
    sheet: MusicSheet,
) : ViewModel() {
    val musicSheet = sheet
    val interactor = InteractorImpl(sheet.persistenceManager)
    val melody: MelodyViewable by mutableStateOf(
        MelodyViewableImpl(
            MusicFactory().createMelody("", TimeSignature.standardTime),
        ),
    )

    init {
        val viewable = ViewableMusicFactory().melody(melody)
        sheet.persistenceManager.subscribableMelody.subscribeAndSynchronize(viewable)
        sheet.persistenceManager.notify(PushFrontEmptyMeasurePersistentRequest())
        println()
    }
}
