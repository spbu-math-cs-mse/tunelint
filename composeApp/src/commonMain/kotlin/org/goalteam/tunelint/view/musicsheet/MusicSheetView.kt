package org.goalteam.tunelint.view.musicsheet

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.goalteam.tunelint.model.changerequest.PersistentRequestFactory
import org.goalteam.tunelint.model.changerequest.impl.PushFrontEmptyMeasurePersistentRequest
import org.goalteam.tunelint.model.core.MusicFactory
import org.goalteam.tunelint.model.core.PrimaryNoteValue
import org.goalteam.tunelint.model.core.impl.NotePointerSimple
import org.goalteam.tunelint.viewmodel.RedactorScreenViewModel

fun addRestOf(
    vm: RedactorScreenViewModel,
    primaryNoteValue: PrimaryNoteValue,
    measure: Int = 0,
) {
    vm.musicSheet.persistenceManager.notify(
        PersistentRequestFactory().addSymbol(
            NotePointerSimple(measure, 0),
            MusicFactory().createRest(primaryNoteValue),
        ),
    )
}

@Composable
fun MusicSheetView(vm: RedactorScreenViewModel) {
    val melody = vm.melody

    vm.musicSheet.persistenceManager.notify(PushFrontEmptyMeasurePersistentRequest())
    addRestOf(vm, PrimaryNoteValue.Whole)
    vm.musicSheet.persistenceManager.notify(PushFrontEmptyMeasurePersistentRequest())
    addRestOf(vm, PrimaryNoteValue.Half)
    addRestOf(vm, PrimaryNoteValue.Quarter)
    addRestOf(vm, PrimaryNoteValue.Eighth)

    Box(modifier = Modifier.verticalScroll(rememberScrollState())) {
        melody.view(
            vm,
            ExternalEvaluatableGeometryDataImpl(
                verticalStep = 20.0,
                upperMargin = 50.0,
                minimalHorizontalStep = 20.0,
                lowerMargin = 50.0,
                fullWidth = 1000.0,
                leftMargin = 50.0,
                rightMargin = 50.0,
            ),
        )
    }
}
