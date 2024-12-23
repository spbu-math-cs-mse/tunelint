package org.goalteam.tunelint.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import org.goalteam.tunelint.interaction.Interactor
import org.goalteam.tunelint.interaction.InteractorImpl
import org.goalteam.tunelint.lint.status.Status
import org.goalteam.tunelint.model.changerequest.subscribeAndSynchronize
import org.goalteam.tunelint.model.core.Clef
import org.goalteam.tunelint.model.core.MusicFactory
import org.goalteam.tunelint.model.core.TimeSignature
import org.goalteam.tunelint.musicsheet.MusicSheet
import org.goalteam.tunelint.view.musicsheet.SheetStyles
import org.goalteam.tunelint.view.musicsheet.viewable.MelodyViewable
import org.goalteam.tunelint.view.musicsheet.viewable.ViewableMusicFactory
import org.goalteam.tunelint.view.musicsheet.viewable.impl.MelodyViewableImpl

class RedactorScreenViewModel(
    sheet: MusicSheet,
) : ViewModel() {
    val musicSheet = sheet
    val interactor : Interactor = InteractorImpl(sheet.persistenceManager)
    val lint = mutableStateOf(listOf<Status>())
    val styles = mutableStateOf(SheetStyles())
    val melody: MelodyViewable by mutableStateOf(
        MelodyViewableImpl(
            MusicFactory().createMelody("", Clef(Clef.ClefType.F), TimeSignature.standardTime),
        ),
    )

    init {
        val viewable = ViewableMusicFactory().melody(melody)
        sheet.persistenceManager.subscribableMelody.subscribeAndSynchronize(viewable)
    }
}
